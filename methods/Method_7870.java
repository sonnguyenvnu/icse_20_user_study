private String getFileName(int index){
  TLObject media=getMedia(index);
  if (media instanceof TLRPC.Photo) {
    media=FileLoader.getClosestPhotoSizeWithSize(((TLRPC.Photo)media).sizes,AndroidUtilities.getPhotoSize());
  }
  return FileLoader.getAttachFileName(media);
}
