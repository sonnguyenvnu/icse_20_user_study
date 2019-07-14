private void forwardMessages(ArrayList<MessageObject> arrayList,boolean fromMyName){
  if (arrayList == null || arrayList.isEmpty()) {
    return;
  }
  if (!fromMyName) {
    AlertsCreator.showSendMediaAlert(SendMessagesHelper.getInstance(currentAccount).sendMessage(arrayList,dialog_id),this);
  }
 else {
    for (    MessageObject object : arrayList) {
      SendMessagesHelper.getInstance(currentAccount).processForwardFromMyName(object,dialog_id);
    }
  }
}
