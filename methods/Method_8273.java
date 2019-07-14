private void checkSecretMessageForLocation(MessageObject messageObject){
  if (messageObject.type != 4 || locationAlertShown || SharedConfig.isSecretMapPreviewSet()) {
    return;
  }
  locationAlertShown=true;
  AlertsCreator.showSecretLocationAlert(getParentActivity(),currentAccount,() -> {
    int count=chatListView.getChildCount();
    for (int a=0; a < count; a++) {
      View view=chatListView.getChildAt(a);
      if (view instanceof ChatMessageCell) {
        ChatMessageCell cell=(ChatMessageCell)view;
        MessageObject message=cell.getMessageObject();
        if (message.type == 4) {
          cell.forceResetMessageObject();
        }
      }
    }
  }
,true);
}
