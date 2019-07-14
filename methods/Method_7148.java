private void revertEditingMessageObject(MessageObject object){
  object.cancelEditing=true;
  object.messageOwner.media=object.previousMedia;
  object.messageOwner.message=object.previousCaption;
  object.messageOwner.entities=object.previousCaptionEntities;
  object.messageOwner.attachPath=object.previousAttachPath;
  object.messageOwner.send_state=MessageObject.MESSAGE_SEND_STATE_SENT;
  object.previousMedia=null;
  object.previousCaption=null;
  object.previousCaptionEntities=null;
  object.previousAttachPath=null;
  object.videoEditedInfo=null;
  object.type=-1;
  object.setType();
  object.caption=null;
  object.generateCaption();
  ArrayList<TLRPC.Message> arr=new ArrayList<>();
  arr.add(object.messageOwner);
  MessagesStorage.getInstance(currentAccount).putMessages(arr,false,true,false,0);
  ArrayList<MessageObject> arrayList=new ArrayList<>();
  arrayList.add(object);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects,object.getDialogId(),arrayList);
}
