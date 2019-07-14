public static void saveMessageThumbs(TLRPC.Message message){
  TLRPC.PhotoSize photoSize=null;
  if (message.media instanceof TLRPC.TL_messageMediaPhoto) {
    for (int a=0, count=message.media.photo.sizes.size(); a < count; a++) {
      TLRPC.PhotoSize size=message.media.photo.sizes.get(a);
      if (size instanceof TLRPC.TL_photoCachedSize) {
        photoSize=size;
        break;
      }
    }
  }
 else   if (message.media instanceof TLRPC.TL_messageMediaDocument) {
    for (int a=0, count=message.media.document.thumbs.size(); a < count; a++) {
      TLRPC.PhotoSize size=message.media.document.thumbs.get(a);
      if (size instanceof TLRPC.TL_photoCachedSize) {
        photoSize=size;
        break;
      }
    }
  }
 else   if (message.media instanceof TLRPC.TL_messageMediaWebPage) {
    if (message.media.webpage.photo != null) {
      for (int a=0, count=message.media.webpage.photo.sizes.size(); a < count; a++) {
        TLRPC.PhotoSize size=message.media.webpage.photo.sizes.get(a);
        if (size instanceof TLRPC.TL_photoCachedSize) {
          photoSize=size;
          break;
        }
      }
    }
  }
  if (photoSize != null && photoSize.bytes != null && photoSize.bytes.length != 0) {
    if (photoSize.location == null || photoSize.location instanceof TLRPC.TL_fileLocationUnavailable) {
      photoSize.location=new TLRPC.TL_fileLocationToBeDeprecated();
      photoSize.location.volume_id=Integer.MIN_VALUE;
      photoSize.location.local_id=SharedConfig.getLastLocalId();
    }
    File file=FileLoader.getPathToAttach(photoSize,true);
    boolean isEncrypted=false;
    if (MessageObject.shouldEncryptPhotoOrVideo(message)) {
      file=new File(file.getAbsolutePath() + ".enc");
      isEncrypted=true;
    }
    if (!file.exists()) {
      try {
        if (isEncrypted) {
          File keyPath=new File(FileLoader.getInternalCacheDir(),file.getName() + ".key");
          RandomAccessFile keyFile=new RandomAccessFile(keyPath,"rws");
          long len=keyFile.length();
          byte[] encryptKey=new byte[32];
          byte[] encryptIv=new byte[16];
          if (len > 0 && len % 48 == 0) {
            keyFile.read(encryptKey,0,32);
            keyFile.read(encryptIv,0,16);
          }
 else {
            Utilities.random.nextBytes(encryptKey);
            Utilities.random.nextBytes(encryptIv);
            keyFile.write(encryptKey);
            keyFile.write(encryptIv);
          }
          keyFile.close();
          Utilities.aesCtrDecryptionByteArray(photoSize.bytes,encryptKey,encryptIv,0,photoSize.bytes.length,0);
        }
        RandomAccessFile writeFile=new RandomAccessFile(file,"rws");
        writeFile.write(photoSize.bytes);
        writeFile.close();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
    TLRPC.TL_photoSize newPhotoSize=new TLRPC.TL_photoSize();
    newPhotoSize.w=photoSize.w;
    newPhotoSize.h=photoSize.h;
    newPhotoSize.location=photoSize.location;
    newPhotoSize.size=photoSize.size;
    newPhotoSize.type=photoSize.type;
    if (message.media instanceof TLRPC.TL_messageMediaPhoto) {
      for (int a=0, count=message.media.photo.sizes.size(); a < count; a++) {
        TLRPC.PhotoSize size=message.media.photo.sizes.get(a);
        if (size instanceof TLRPC.TL_photoCachedSize) {
          message.media.photo.sizes.set(a,newPhotoSize);
          break;
        }
      }
    }
 else     if (message.media instanceof TLRPC.TL_messageMediaDocument) {
      for (int a=0, count=message.media.document.thumbs.size(); a < count; a++) {
        TLRPC.PhotoSize size=message.media.document.thumbs.get(a);
        if (size instanceof TLRPC.TL_photoCachedSize) {
          message.media.document.thumbs.set(a,newPhotoSize);
          break;
        }
      }
    }
 else     if (message.media instanceof TLRPC.TL_messageMediaWebPage) {
      for (int a=0, count=message.media.webpage.photo.sizes.size(); a < count; a++) {
        TLRPC.PhotoSize size=message.media.webpage.photo.sizes.get(a);
        if (size instanceof TLRPC.TL_photoCachedSize) {
          message.media.webpage.photo.sizes.set(a,newPhotoSize);
          break;
        }
      }
    }
  }
}
