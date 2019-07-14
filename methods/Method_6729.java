public String getFileName(){
  if (messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
    return FileLoader.getAttachFileName(messageOwner.media.document);
  }
 else   if (messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
    ArrayList<TLRPC.PhotoSize> sizes=messageOwner.media.photo.sizes;
    if (sizes.size() > 0) {
      TLRPC.PhotoSize sizeFull=FileLoader.getClosestPhotoSizeWithSize(sizes,AndroidUtilities.getPhotoSize());
      if (sizeFull != null) {
        return FileLoader.getAttachFileName(sizeFull);
      }
    }
  }
 else   if (messageOwner.media instanceof TLRPC.TL_messageMediaWebPage) {
    return FileLoader.getAttachFileName(messageOwner.media.webpage.document);
  }
  return "";
}
