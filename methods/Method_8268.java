private void sendUriAsDocument(Uri uri){
  if (uri == null) {
    return;
  }
  String extractUriFrom=uri.toString();
  if (extractUriFrom.contains("com.google.android.apps.photos.contentprovider")) {
    try {
      String firstExtraction=extractUriFrom.split("/1/")[1];
      int index=firstExtraction.indexOf("/ACTUAL");
      if (index != -1) {
        firstExtraction=firstExtraction.substring(0,index);
        String secondExtraction=URLDecoder.decode(firstExtraction,"UTF-8");
        uri=Uri.parse(secondExtraction);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  String tempPath=AndroidUtilities.getPath(uri);
  String originalPath=tempPath;
  if (tempPath == null) {
    originalPath=uri.toString();
    tempPath=MediaController.copyFileToCache(uri,"file");
  }
  if (tempPath == null) {
    showAttachmentError();
    return;
  }
  fillEditingMediaWithCaption(null,null);
  SendMessagesHelper.prepareSendingDocument(tempPath,originalPath,null,null,null,dialog_id,replyingMessageObject,null,editingMessageObject);
  hideFieldPanel(false);
}
