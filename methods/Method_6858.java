public void saveGif(Object parentObject,TLRPC.Document document){
  if (parentObject == null || !MessageObject.isGifDocument(document)) {
    return;
  }
  TLRPC.TL_messages_saveGif req=new TLRPC.TL_messages_saveGif();
  req.id=new TLRPC.TL_inputDocument();
  req.id.id=document.id;
  req.id.access_hash=document.access_hash;
  req.id.file_reference=document.file_reference;
  if (req.id.file_reference == null) {
    req.id.file_reference=new byte[0];
  }
  req.unsave=false;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error != null && FileRefController.isFileRefError(error.text) && parentObject != null) {
      FileRefController.getInstance(currentAccount).requestReference(parentObject,req);
    }
  }
);
}
