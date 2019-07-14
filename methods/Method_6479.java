protected boolean processRequestResult(RequestInfo requestInfo,TLRPC.TL_error error){
  if (state != stateDownloading) {
    if (BuildVars.DEBUG_VERSION) {
      FileLog.d("trying to write to finished file " + cacheFileFinal + " offset " + requestInfo.offset);
    }
    return false;
  }
  requestInfos.remove(requestInfo);
  if (error == null) {
    try {
      if (notLoadedBytesRanges == null && downloadedBytes != requestInfo.offset) {
        delayRequestInfo(requestInfo);
        return false;
      }
      NativeByteBuffer bytes;
      if (requestInfo.response != null) {
        bytes=requestInfo.response.bytes;
      }
 else       if (requestInfo.responseWeb != null) {
        bytes=requestInfo.responseWeb.bytes;
      }
 else       if (requestInfo.responseCdn != null) {
        bytes=requestInfo.responseCdn.bytes;
      }
 else {
        bytes=null;
      }
      if (bytes == null || bytes.limit() == 0) {
        onFinishLoadingFile(true);
        return false;
      }
      int currentBytesSize=bytes.limit();
      if (isCdn) {
        int cdnCheckPart=requestInfo.offset / cdnChunkCheckSize;
        int fileOffset=cdnCheckPart * cdnChunkCheckSize;
        TLRPC.TL_fileHash hash=cdnHashes != null ? cdnHashes.get(fileOffset) : null;
        if (hash == null) {
          delayRequestInfo(requestInfo);
          requestFileOffsets(fileOffset);
          return true;
        }
      }
      if (requestInfo.responseCdn != null) {
        int offset=requestInfo.offset / 16;
        cdnIv[15]=(byte)(offset & 0xff);
        cdnIv[14]=(byte)((offset >> 8) & 0xff);
        cdnIv[13]=(byte)((offset >> 16) & 0xff);
        cdnIv[12]=(byte)((offset >> 24) & 0xff);
        Utilities.aesCtrDecryption(bytes.buffer,cdnKey,cdnIv,0,bytes.limit());
      }
      boolean finishedDownloading;
      if (isPreloadVideoOperation) {
        preloadStream.writeInt(requestInfo.offset);
        preloadStream.writeInt(currentBytesSize);
        preloadStreamFileOffset+=8;
        FileChannel channel=preloadStream.getChannel();
        channel.write(bytes.buffer);
        if (BuildVars.DEBUG_VERSION) {
          FileLog.d("save preload file part " + cacheFilePreload + " offset " + requestInfo.offset + " size " + currentBytesSize);
        }
        if (preloadedBytesRanges == null) {
          preloadedBytesRanges=new SparseArray<>();
        }
        preloadedBytesRanges.put(requestInfo.offset,new PreloadRange(preloadStreamFileOffset,requestInfo.offset,currentBytesSize));
        totalPreloadedBytes+=currentBytesSize;
        preloadStreamFileOffset+=currentBytesSize;
        if (moovFound == 0) {
          int offset=findNextPreloadDownloadOffset(nextAtomOffset,requestInfo.offset,bytes);
          if (offset < 0) {
            offset*=-1;
            nextPreloadDownloadOffset+=currentDownloadChunkSize;
            if (nextPreloadDownloadOffset < totalBytesCount / 2) {
              preloadNotRequestedBytesCount=foundMoovSize=preloadMaxBytes / 2 + offset;
              moovFound=1;
            }
 else {
              preloadNotRequestedBytesCount=foundMoovSize=preloadMaxBytes;
              moovFound=2;
            }
            nextPreloadDownloadOffset=-1;
          }
 else {
            nextPreloadDownloadOffset=offset / currentDownloadChunkSize * currentDownloadChunkSize;
          }
          nextAtomOffset=offset;
        }
        preloadStream.writeInt(foundMoovSize);
        preloadStream.writeInt(nextPreloadDownloadOffset);
        preloadStream.writeInt(nextAtomOffset);
        preloadStreamFileOffset+=12;
        finishedDownloading=nextPreloadDownloadOffset == 0 || moovFound != 0 && foundMoovSize < 0 || totalPreloadedBytes > preloadMaxBytes || nextPreloadDownloadOffset >= totalBytesCount;
        if (finishedDownloading) {
          preloadStream.seek(0);
          preloadStream.write((byte)1);
        }
 else         if (moovFound != 0) {
          foundMoovSize-=currentDownloadChunkSize;
        }
      }
 else {
        downloadedBytes+=currentBytesSize;
        if (totalBytesCount > 0) {
          finishedDownloading=downloadedBytes >= totalBytesCount;
        }
 else {
          finishedDownloading=currentBytesSize != currentDownloadChunkSize || (totalBytesCount == downloadedBytes || downloadedBytes % currentDownloadChunkSize != 0) && (totalBytesCount <= 0 || totalBytesCount <= downloadedBytes);
        }
        if (key != null) {
          Utilities.aesIgeEncryption(bytes.buffer,key,iv,false,true,0,bytes.limit());
          if (finishedDownloading && bytesCountPadding != 0) {
            bytes.limit(bytes.limit() - bytesCountPadding);
          }
        }
        if (encryptFile) {
          int offset=requestInfo.offset / 16;
          encryptIv[15]=(byte)(offset & 0xff);
          encryptIv[14]=(byte)((offset >> 8) & 0xff);
          encryptIv[13]=(byte)((offset >> 16) & 0xff);
          encryptIv[12]=(byte)((offset >> 24) & 0xff);
          Utilities.aesCtrDecryption(bytes.buffer,encryptKey,encryptIv,0,bytes.limit());
        }
        if (notLoadedBytesRanges != null) {
          fileOutputStream.seek(requestInfo.offset);
          if (BuildVars.DEBUG_VERSION) {
            FileLog.d("save file part " + cacheFileFinal + " offset " + requestInfo.offset);
          }
        }
        FileChannel channel=fileOutputStream.getChannel();
        channel.write(bytes.buffer);
        addPart(notLoadedBytesRanges,requestInfo.offset,requestInfo.offset + currentBytesSize,true);
        if (isCdn) {
          int cdnCheckPart=requestInfo.offset / cdnChunkCheckSize;
          int size=notCheckedCdnRanges.size();
          Range range;
          boolean checked=true;
          for (int a=0; a < size; a++) {
            range=notCheckedCdnRanges.get(a);
            if (range.start <= cdnCheckPart && cdnCheckPart <= range.end) {
              checked=false;
              break;
            }
          }
          if (!checked) {
            int fileOffset=cdnCheckPart * cdnChunkCheckSize;
            int availableSize=getDownloadedLengthFromOffsetInternal(notLoadedBytesRanges,fileOffset,cdnChunkCheckSize);
            if (availableSize != 0 && (availableSize == cdnChunkCheckSize || totalBytesCount > 0 && availableSize == totalBytesCount - fileOffset || totalBytesCount <= 0 && finishedDownloading)) {
              TLRPC.TL_fileHash hash=cdnHashes.get(fileOffset);
              if (fileReadStream == null) {
                cdnCheckBytes=new byte[cdnChunkCheckSize];
                fileReadStream=new RandomAccessFile(cacheFileTemp,"r");
              }
              fileReadStream.seek(fileOffset);
              fileReadStream.readFully(cdnCheckBytes,0,availableSize);
              byte[] sha256=Utilities.computeSHA256(cdnCheckBytes,0,availableSize);
              if (!Arrays.equals(sha256,hash.hash)) {
                if (BuildVars.LOGS_ENABLED) {
                  if (location != null) {
                    FileLog.e("invalid cdn hash " + location + " id = " + location.id + " local_id = " + location.local_id + " access_hash = " + location.access_hash + " volume_id = " + location.volume_id + " secret = " + location.secret);
                  }
 else                   if (webLocation != null) {
                    FileLog.e("invalid cdn hash  " + webLocation + " id = " + getFileName());
                  }
                }
                onFail(false,0);
                cacheFileTemp.delete();
                return false;
              }
              cdnHashes.remove(fileOffset);
              addPart(notCheckedCdnRanges,cdnCheckPart,cdnCheckPart + 1,false);
            }
          }
        }
        if (fiv != null) {
          fiv.seek(0);
          fiv.write(iv);
        }
        if (totalBytesCount > 0 && state == stateDownloading) {
          copyNotLoadedRanges();
          delegate.didChangedLoadProgress(FileLoadOperation.this,Math.min(1.0f,(float)downloadedBytes / (float)totalBytesCount));
        }
      }
      for (int a=0; a < delayedRequestInfos.size(); a++) {
        RequestInfo delayedRequestInfo=delayedRequestInfos.get(a);
        if (notLoadedBytesRanges != null || downloadedBytes == delayedRequestInfo.offset) {
          delayedRequestInfos.remove(a);
          if (!processRequestResult(delayedRequestInfo,null)) {
            if (delayedRequestInfo.response != null) {
              delayedRequestInfo.response.disableFree=false;
              delayedRequestInfo.response.freeResources();
            }
 else             if (delayedRequestInfo.responseWeb != null) {
              delayedRequestInfo.responseWeb.disableFree=false;
              delayedRequestInfo.responseWeb.freeResources();
            }
 else             if (delayedRequestInfo.responseCdn != null) {
              delayedRequestInfo.responseCdn.disableFree=false;
              delayedRequestInfo.responseCdn.freeResources();
            }
          }
          break;
        }
      }
      if (finishedDownloading) {
        onFinishLoadingFile(true);
      }
 else {
        startDownloadRequest();
      }
    }
 catch (    Exception e) {
      onFail(false,0);
      FileLog.e(e);
    }
  }
 else {
    if (error.text.contains("FILE_MIGRATE_")) {
      String errorMsg=error.text.replace("FILE_MIGRATE_","");
      Scanner scanner=new Scanner(errorMsg);
      scanner.useDelimiter("");
      Integer val;
      try {
        val=scanner.nextInt();
      }
 catch (      Exception e) {
        val=null;
      }
      if (val == null) {
        onFail(false,0);
      }
 else {
        datacenterId=val;
        requestedBytesCount=downloadedBytes=0;
        startDownloadRequest();
      }
    }
 else     if (error.text.contains("OFFSET_INVALID")) {
      if (downloadedBytes % currentDownloadChunkSize == 0) {
        try {
          onFinishLoadingFile(true);
        }
 catch (        Exception e) {
          FileLog.e(e);
          onFail(false,0);
        }
      }
 else {
        onFail(false,0);
      }
    }
 else     if (error.text.contains("RETRY_LIMIT")) {
      onFail(false,2);
    }
 else {
      if (BuildVars.LOGS_ENABLED) {
        if (location != null) {
          FileLog.e(error.text + " " + location + " id = " + location.id + " local_id = " + location.local_id + " access_hash = " + location.access_hash + " volume_id = " + location.volume_id + " secret = " + location.secret);
        }
 else         if (webLocation != null) {
          FileLog.e(error.text + " " + webLocation + " id = " + getFileName());
        }
      }
      onFail(false,0);
    }
  }
  return false;
}
