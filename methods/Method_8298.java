private void updateInformationForScreenshotDetector(){
  if (currentUser == null) {
    return;
  }
  ArrayList<Long> visibleMessages;
  int messageId=0;
  if (currentEncryptedChat != null) {
    visibleMessages=new ArrayList<>();
    if (chatListView != null) {
      int count=chatListView.getChildCount();
      for (int a=0; a < count; a++) {
        View view=chatListView.getChildAt(a);
        MessageObject object=null;
        if (view instanceof ChatMessageCell) {
          ChatMessageCell cell=(ChatMessageCell)view;
          object=cell.getMessageObject();
        }
        if (object != null && object.getId() < 0 && object.messageOwner.random_id != 0) {
          visibleMessages.add(object.messageOwner.random_id);
        }
      }
    }
    MediaController.getInstance().setLastVisibleMessageIds(currentAccount,chatEnterTime,chatLeaveTime,currentUser,currentEncryptedChat,visibleMessages,messageId);
  }
 else {
    SecretMediaViewer viewer=SecretMediaViewer.getInstance();
    MessageObject messageObject=viewer.getCurrentMessageObject();
    if (messageObject != null && !messageObject.isOut()) {
      MediaController.getInstance().setLastVisibleMessageIds(currentAccount,viewer.getOpenTime(),viewer.getCloseTime(),currentUser,null,null,messageObject.getId());
    }
  }
}
