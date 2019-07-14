private void requestReference(RequestInfo requestInfo){
  if (requestingReference) {
    return;
  }
  clearOperaion(requestInfo,false);
  requestingReference=true;
  if (parentObject instanceof MessageObject) {
    MessageObject messageObject=(MessageObject)parentObject;
    if (messageObject.getId() < 0 && messageObject.messageOwner.media.webpage != null) {
      parentObject=messageObject.messageOwner.media.webpage;
    }
  }
  FileRefController.getInstance(currentAccount).requestReference(parentObject,location,this,requestInfo);
}
