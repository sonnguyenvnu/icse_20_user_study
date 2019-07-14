protected void forceShowPopupForReply(){
  notificationsQueue.postRunnable(() -> {
    final ArrayList<MessageObject> popupArray=new ArrayList<>();
    for (int a=0; a < pushMessages.size(); a++) {
      MessageObject messageObject=pushMessages.get(a);
      long dialog_id=messageObject.getDialogId();
      if (messageObject.messageOwner.mentioned && messageObject.messageOwner.action instanceof TLRPC.TL_messageActionPinMessage || (int)dialog_id == 0 || messageObject.messageOwner.to_id.channel_id != 0 && !messageObject.isMegagroup()) {
        continue;
      }
      popupArray.add(0,messageObject);
    }
    if (!popupArray.isEmpty() && !AndroidUtilities.needShowPasscode(false)) {
      AndroidUtilities.runOnUIThread(() -> {
        popupReplyMessages=popupArray;
        Intent popupIntent=new Intent(ApplicationLoader.applicationContext,PopupNotificationActivity.class);
        popupIntent.putExtra("force",true);
        popupIntent.putExtra("currentAccount",currentAccount);
        popupIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NO_USER_ACTION | Intent.FLAG_FROM_BACKGROUND);
        ApplicationLoader.applicationContext.startActivity(popupIntent);
        Intent it=new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        ApplicationLoader.applicationContext.sendBroadcast(it);
      }
);
    }
  }
);
}
