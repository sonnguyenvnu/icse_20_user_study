private void saveMessageToGallery(MessageObject messageObject){
  String path=messageObject.messageOwner.attachPath;
  if (!TextUtils.isEmpty(path)) {
    File temp=new File(path);
    if (!temp.exists()) {
      path=null;
    }
  }
  if (TextUtils.isEmpty(path)) {
    path=FileLoader.getPathToMessage(messageObject.messageOwner).toString();
  }
  MediaController.saveFile(path,getParentActivity(),messageObject.isVideo() ? 1 : 0,null,null);
}
