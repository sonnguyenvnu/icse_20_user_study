private void checkAutoDownloadMessage(MessageObject object){
  if (object.mediaExists) {
    return;
  }
  TLRPC.Message message=object.messageOwner;
  int canDownload=DownloadController.getInstance(currentAccount).canDownloadMedia(message);
  if (canDownload == 0) {
    return;
  }
  TLRPC.Document document=object.getDocument();
  TLRPC.PhotoSize photo=document == null ? FileLoader.getClosestPhotoSizeWithSize(object.photoThumbs,AndroidUtilities.getPhotoSize()) : null;
  if (document == null && photo == null) {
    return;
  }
  if (canDownload == 2 || canDownload == 1 && object.isVideo()) {
    if (document != null && currentEncryptedChat == null && !object.shouldEncryptPhotoOrVideo() && object.canStreamVideo()) {
      FileLoader.getInstance(currentAccount).loadFile(document,object,0,10);
    }
  }
 else {
    if (document != null) {
      FileLoader.getInstance(currentAccount).loadFile(document,object,0,MessageObject.isVideoDocument(document) && object.shouldEncryptPhotoOrVideo() ? 2 : 0);
    }
 else {
      FileLoader.getInstance(currentAccount).loadFile(ImageLocation.getForObject(photo,object.photoThumbsObject),object,null,0,object.shouldEncryptPhotoOrVideo() ? 2 : 0);
    }
  }
}
