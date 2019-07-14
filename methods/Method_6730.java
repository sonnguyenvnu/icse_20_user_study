public int getFileType(){
  if (isVideo()) {
    return FileLoader.MEDIA_DIR_VIDEO;
  }
 else   if (isVoice()) {
    return FileLoader.MEDIA_DIR_AUDIO;
  }
 else   if (messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
    return FileLoader.MEDIA_DIR_DOCUMENT;
  }
 else   if (messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
    return FileLoader.MEDIA_DIR_IMAGE;
  }
  return FileLoader.MEDIA_DIR_CACHE;
}
