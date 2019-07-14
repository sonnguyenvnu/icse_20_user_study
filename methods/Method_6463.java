public static File getPathToAttach(TLObject attach,String ext,boolean forceCache){
  File dir=null;
  if (forceCache) {
    dir=getDirectory(MEDIA_DIR_CACHE);
  }
 else {
    if (attach instanceof TLRPC.Document) {
      TLRPC.Document document=(TLRPC.Document)attach;
      if (document.key != null) {
        dir=getDirectory(MEDIA_DIR_CACHE);
      }
 else {
        if (MessageObject.isVoiceDocument(document)) {
          dir=getDirectory(MEDIA_DIR_AUDIO);
        }
 else         if (MessageObject.isVideoDocument(document)) {
          dir=getDirectory(MEDIA_DIR_VIDEO);
        }
 else {
          dir=getDirectory(MEDIA_DIR_DOCUMENT);
        }
      }
    }
 else     if (attach instanceof TLRPC.Photo) {
      TLRPC.PhotoSize photoSize=getClosestPhotoSizeWithSize(((TLRPC.Photo)attach).sizes,AndroidUtilities.getPhotoSize());
      return getPathToAttach(photoSize,ext,forceCache);
    }
 else     if (attach instanceof TLRPC.PhotoSize) {
      TLRPC.PhotoSize photoSize=(TLRPC.PhotoSize)attach;
      if (photoSize instanceof TLRPC.TL_photoStrippedSize) {
        dir=null;
      }
 else       if (photoSize.location == null || photoSize.location.key != null || photoSize.location.volume_id == Integer.MIN_VALUE && photoSize.location.local_id < 0 || photoSize.size < 0) {
        dir=getDirectory(MEDIA_DIR_CACHE);
      }
 else {
        dir=getDirectory(MEDIA_DIR_IMAGE);
      }
    }
 else     if (attach instanceof TLRPC.FileLocation) {
      TLRPC.FileLocation fileLocation=(TLRPC.FileLocation)attach;
      if (fileLocation.key != null || fileLocation.volume_id == Integer.MIN_VALUE && fileLocation.local_id < 0) {
        dir=getDirectory(MEDIA_DIR_CACHE);
      }
 else {
        dir=getDirectory(MEDIA_DIR_IMAGE);
      }
    }
 else     if (attach instanceof WebFile) {
      WebFile document=(WebFile)attach;
      if (document.mime_type.startsWith("image/")) {
        dir=getDirectory(MEDIA_DIR_IMAGE);
      }
 else       if (document.mime_type.startsWith("audio/")) {
        dir=getDirectory(MEDIA_DIR_AUDIO);
      }
 else       if (document.mime_type.startsWith("video/")) {
        dir=getDirectory(MEDIA_DIR_VIDEO);
      }
 else {
        dir=getDirectory(MEDIA_DIR_DOCUMENT);
      }
    }
 else     if (attach instanceof TLRPC.TL_secureFile || attach instanceof SecureDocument) {
      dir=getDirectory(MEDIA_DIR_CACHE);
    }
  }
  if (dir == null) {
    return new File("");
  }
  return new File(dir,getAttachFileName(attach,ext));
}
