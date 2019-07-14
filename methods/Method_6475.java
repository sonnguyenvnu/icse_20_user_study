public boolean start(final FileLoadOperationStream stream,final int streamOffset){
  if (currentDownloadChunkSize == 0) {
    currentDownloadChunkSize=totalBytesCount >= bigFileSizeFrom ? downloadChunkSizeBig : downloadChunkSize;
    currentMaxDownloadRequests=totalBytesCount >= bigFileSizeFrom ? maxDownloadRequestsBig : maxDownloadRequests;
  }
  final boolean alreadyStarted=state != stateIdle;
  final boolean wasPaused=paused;
  paused=false;
  if (stream != null) {
    Utilities.stageQueue.postRunnable(() -> {
      if (streamListeners == null) {
        streamListeners=new ArrayList<>();
      }
      streamStartOffset=streamOffset / currentDownloadChunkSize * currentDownloadChunkSize;
      streamListeners.add(stream);
      if (alreadyStarted) {
        if (preloadedBytesRanges != null && getDownloadedLengthFromOffsetInternal(notLoadedBytesRanges,streamStartOffset,1) == 0) {
          if (preloadedBytesRanges.get(streamStartOffset) != null) {
            nextPartWasPreloaded=true;
          }
        }
        startDownloadRequest();
        nextPartWasPreloaded=false;
      }
    }
);
  }
 else   if (wasPaused && alreadyStarted) {
    Utilities.stageQueue.postRunnable(this::startDownloadRequest);
  }
  if (alreadyStarted) {
    return wasPaused;
  }
  if (location == null && webLocation == null) {
    onFail(true,0);
    return false;
  }
  streamStartOffset=streamOffset / currentDownloadChunkSize * currentDownloadChunkSize;
  if (allowDisordererFileSave && totalBytesCount > 0 && totalBytesCount > currentDownloadChunkSize) {
    notLoadedBytesRanges=new ArrayList<>();
    notRequestedBytesRanges=new ArrayList<>();
  }
  String fileNameFinal;
  String fileNameTemp;
  String fileNameParts=null;
  String fileNamePreload=null;
  String fileNameIv=null;
  if (webLocation != null) {
    String md5=Utilities.MD5(webFile.url);
    if (encryptFile) {
      fileNameTemp=md5 + ".temp.enc";
      fileNameFinal=md5 + "." + ext + ".enc";
      if (key != null) {
        fileNameIv=md5 + ".iv.enc";
      }
    }
 else {
      fileNameTemp=md5 + ".temp";
      fileNameFinal=md5 + "." + ext;
      if (key != null) {
        fileNameIv=md5 + ".iv";
      }
    }
  }
 else {
    if (location.volume_id != 0 && location.local_id != 0) {
      if (datacenterId == Integer.MIN_VALUE || location.volume_id == Integer.MIN_VALUE || datacenterId == 0) {
        onFail(true,0);
        return false;
      }
      if (encryptFile) {
        fileNameTemp=location.volume_id + "_" + location.local_id + ".temp.enc";
        fileNameFinal=location.volume_id + "_" + location.local_id + "." + ext + ".enc";
        if (key != null) {
          fileNameIv=location.volume_id + "_" + location.local_id + ".iv.enc";
        }
      }
 else {
        fileNameTemp=location.volume_id + "_" + location.local_id + ".temp";
        fileNameFinal=location.volume_id + "_" + location.local_id + "." + ext;
        if (key != null) {
          fileNameIv=location.volume_id + "_" + location.local_id + ".iv";
        }
        if (notLoadedBytesRanges != null) {
          fileNameParts=location.volume_id + "_" + location.local_id + ".pt";
        }
        fileNamePreload=location.volume_id + "_" + location.local_id + ".preload";
      }
    }
 else {
      if (datacenterId == 0 || location.id == 0) {
        onFail(true,0);
        return false;
      }
      if (encryptFile) {
        fileNameTemp=datacenterId + "_" + location.id + ".temp.enc";
        fileNameFinal=datacenterId + "_" + location.id + ext + ".enc";
        if (key != null) {
          fileNameIv=datacenterId + "_" + location.id + ".iv.enc";
        }
      }
 else {
        fileNameTemp=datacenterId + "_" + location.id + ".temp";
        fileNameFinal=datacenterId + "_" + location.id + ext;
        if (key != null) {
          fileNameIv=datacenterId + "_" + location.id + ".iv";
        }
        if (notLoadedBytesRanges != null) {
          fileNameParts=datacenterId + "_" + location.id + ".pt";
        }
        fileNamePreload=datacenterId + "_" + location.id + ".preload";
      }
    }
  }
  requestInfos=new ArrayList<>(currentMaxDownloadRequests);
  delayedRequestInfos=new ArrayList<>(currentMaxDownloadRequests - 1);
  state=stateDownloading;
  cacheFileFinal=new File(storePath,fileNameFinal);
  boolean finalFileExist=cacheFileFinal.exists();
  if (finalFileExist && totalBytesCount != 0 && totalBytesCount != cacheFileFinal.length()) {
    cacheFileFinal.delete();
    finalFileExist=false;
  }
  if (!finalFileExist) {
    cacheFileTemp=new File(tempPath,fileNameTemp);
    boolean newKeyGenerated=false;
    if (encryptFile) {
      File keyFile=new File(FileLoader.getInternalCacheDir(),fileNameFinal + ".key");
      try {
        RandomAccessFile file=new RandomAccessFile(keyFile,"rws");
        long len=keyFile.length();
        encryptKey=new byte[32];
        encryptIv=new byte[16];
        if (len > 0 && len % 48 == 0) {
          file.read(encryptKey,0,32);
          file.read(encryptIv,0,16);
        }
 else {
          Utilities.random.nextBytes(encryptKey);
          Utilities.random.nextBytes(encryptIv);
          file.write(encryptKey);
          file.write(encryptIv);
          newKeyGenerated=true;
        }
        try {
          file.getChannel().close();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        file.close();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
    boolean[] preloaded=new boolean[]{false};
    if (supportsPreloading && fileNamePreload != null) {
      cacheFilePreload=new File(tempPath,fileNamePreload);
      boolean closeStream=false;
      try {
        preloadStream=new RandomAccessFile(cacheFilePreload,"rws");
        long len=preloadStream.length();
        int readOffset=0;
        preloadStreamFileOffset=1;
        if (len - readOffset > 1) {
          preloaded[0]=preloadStream.readByte() != 0;
          readOffset+=1;
          while (readOffset < len) {
            if (len - readOffset < 4) {
              break;
            }
            int offset=preloadStream.readInt();
            readOffset+=4;
            if (len - readOffset < 4 || offset < 0 || offset > totalBytesCount) {
              break;
            }
            int size=preloadStream.readInt();
            readOffset+=4;
            if (len - readOffset < size || size > currentDownloadChunkSize) {
              break;
            }
            PreloadRange range=new PreloadRange(readOffset,offset,size);
            readOffset+=size;
            preloadStream.seek(readOffset);
            if (len - readOffset < 12) {
              break;
            }
            foundMoovSize=preloadStream.readInt();
            if (foundMoovSize != 0) {
              moovFound=nextPreloadDownloadOffset > totalBytesCount / 2 ? 2 : 1;
              preloadNotRequestedBytesCount=foundMoovSize;
            }
            nextPreloadDownloadOffset=preloadStream.readInt();
            nextAtomOffset=preloadStream.readInt();
            readOffset+=12;
            if (preloadedBytesRanges == null) {
              preloadedBytesRanges=new SparseArray<>();
            }
            if (requestedPreloadedBytesRanges == null) {
              requestedPreloadedBytesRanges=new SparseIntArray();
            }
            preloadedBytesRanges.put(offset,range);
            requestedPreloadedBytesRanges.put(offset,1);
            totalPreloadedBytes+=size;
            preloadStreamFileOffset+=20 + size;
          }
        }
        preloadStream.seek(preloadStreamFileOffset);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      if (!isPreloadVideoOperation && preloadedBytesRanges == null) {
        cacheFilePreload=null;
        try {
          if (preloadStream != null) {
            try {
              preloadStream.getChannel().close();
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
            preloadStream.close();
            preloadStream=null;
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
    if (fileNameParts != null) {
      cacheFileParts=new File(tempPath,fileNameParts);
      try {
        filePartsStream=new RandomAccessFile(cacheFileParts,"rws");
        long len=filePartsStream.length();
        if (len % 8 == 4) {
          len-=4;
          int count=filePartsStream.readInt();
          if (count <= len / 2) {
            for (int a=0; a < count; a++) {
              int start=filePartsStream.readInt();
              int end=filePartsStream.readInt();
              notLoadedBytesRanges.add(new Range(start,end));
              notRequestedBytesRanges.add(new Range(start,end));
            }
          }
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
    if (cacheFileTemp.exists()) {
      if (newKeyGenerated) {
        cacheFileTemp.delete();
      }
 else {
        long totalDownloadedLen=cacheFileTemp.length();
        if (fileNameIv != null && (totalDownloadedLen % currentDownloadChunkSize) != 0) {
          requestedBytesCount=downloadedBytes=0;
        }
 else {
          requestedBytesCount=downloadedBytes=((int)cacheFileTemp.length()) / currentDownloadChunkSize * currentDownloadChunkSize;
        }
        if (notLoadedBytesRanges != null && notLoadedBytesRanges.isEmpty()) {
          notLoadedBytesRanges.add(new Range(downloadedBytes,totalBytesCount));
          notRequestedBytesRanges.add(new Range(downloadedBytes,totalBytesCount));
        }
      }
    }
 else     if (notLoadedBytesRanges != null && notLoadedBytesRanges.isEmpty()) {
      notLoadedBytesRanges.add(new Range(0,totalBytesCount));
      notRequestedBytesRanges.add(new Range(0,totalBytesCount));
    }
    if (notLoadedBytesRanges != null) {
      downloadedBytes=totalBytesCount;
      int size=notLoadedBytesRanges.size();
      Range range;
      for (int a=0; a < size; a++) {
        range=notLoadedBytesRanges.get(a);
        downloadedBytes-=(range.end - range.start);
      }
      requestedBytesCount=downloadedBytes;
    }
    if (BuildVars.LOGS_ENABLED) {
      if (isPreloadVideoOperation) {
        FileLog.d("start preloading file to temp = " + cacheFileTemp);
      }
 else {
        FileLog.d("start loading file to temp = " + cacheFileTemp + " final = " + cacheFileFinal);
      }
    }
    if (fileNameIv != null) {
      cacheIvTemp=new File(tempPath,fileNameIv);
      try {
        fiv=new RandomAccessFile(cacheIvTemp,"rws");
        if (downloadedBytes != 0 && !newKeyGenerated) {
          long len=cacheIvTemp.length();
          if (len > 0 && len % 32 == 0) {
            fiv.read(iv,0,32);
          }
 else {
            requestedBytesCount=downloadedBytes=0;
          }
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
        requestedBytesCount=downloadedBytes=0;
      }
    }
    if (!isPreloadVideoOperation && downloadedBytes != 0 && totalBytesCount > 0) {
      copyNotLoadedRanges();
      delegate.didChangedLoadProgress(FileLoadOperation.this,Math.min(1.0f,(float)downloadedBytes / (float)totalBytesCount));
    }
    try {
      fileOutputStream=new RandomAccessFile(cacheFileTemp,"rws");
      if (downloadedBytes != 0) {
        fileOutputStream.seek(downloadedBytes);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    if (fileOutputStream == null) {
      onFail(true,0);
      return false;
    }
    started=true;
    Utilities.stageQueue.postRunnable(() -> {
      if (totalBytesCount != 0 && (isPreloadVideoOperation && preloaded[0] || downloadedBytes == totalBytesCount)) {
        try {
          onFinishLoadingFile(false);
        }
 catch (        Exception e) {
          onFail(true,0);
        }
      }
 else {
        startDownloadRequest();
      }
    }
);
  }
 else {
    started=true;
    try {
      onFinishLoadingFile(false);
    }
 catch (    Exception e) {
      onFail(true,0);
    }
  }
  return true;
}
