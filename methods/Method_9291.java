@Override public void didSelectDialogs(DialogsActivity dialogsFragment,ArrayList<Long> dids,CharSequence message,boolean param){
  final long did=dids.get(0);
  int lower_part=(int)did;
  int high_id=(int)(did >> 32);
  Bundle args=new Bundle();
  final int account=dialogsFragment != null ? dialogsFragment.getCurrentAccount() : currentAccount;
  args.putBoolean("scrollToTopOnResume",true);
  if (!AndroidUtilities.isTablet()) {
    NotificationCenter.getInstance(account).postNotificationName(NotificationCenter.closeChats);
  }
  if (lower_part != 0) {
    if (high_id == 1) {
      args.putInt("chat_id",lower_part);
    }
 else {
      if (lower_part > 0) {
        args.putInt("user_id",lower_part);
      }
 else       if (lower_part < 0) {
        args.putInt("chat_id",-lower_part);
      }
    }
  }
 else {
    args.putInt("enc_id",high_id);
  }
  if (!MessagesController.getInstance(account).checkCanOpenChat(args,dialogsFragment)) {
    return;
  }
  final ChatActivity fragment=new ChatActivity(args);
  if (contactsToSend != null && contactsToSend.size() == 1) {
    if (contactsToSend.size() == 1) {
      PhonebookShareActivity contactFragment=new PhonebookShareActivity(null,contactsToSendUri,null,null);
      contactFragment.setDelegate(user -> {
        actionBarLayout.presentFragment(fragment,true,false,true,false);
        SendMessagesHelper.getInstance(account).sendMessage(user,did,null,null,null);
      }
);
      actionBarLayout.presentFragment(contactFragment,dialogsFragment != null,dialogsFragment == null,true,false);
    }
  }
 else {
    actionBarLayout.presentFragment(fragment,dialogsFragment != null,dialogsFragment == null,true,false);
    if (videoPath != null) {
      fragment.openVideoEditor(videoPath,sendingText);
      sendingText=null;
    }
    if (photoPathsArray != null) {
      if (sendingText != null && sendingText.length() <= 1024 && photoPathsArray.size() == 1) {
        photoPathsArray.get(0).caption=sendingText;
        sendingText=null;
      }
      SendMessagesHelper.prepareSendingMedia(photoPathsArray,did,null,null,false,false,null);
    }
    if (documentsPathsArray != null || documentsUrisArray != null) {
      String caption=null;
      if (sendingText != null && sendingText.length() <= 1024 && ((documentsPathsArray != null ? documentsPathsArray.size() : 0) + (documentsUrisArray != null ? documentsUrisArray.size() : 0)) == 1) {
        caption=sendingText;
        sendingText=null;
      }
      SendMessagesHelper.prepareSendingDocuments(documentsPathsArray,documentsOriginalPathsArray,documentsUrisArray,caption,documentsMimeType,did,null,null,null);
    }
    if (sendingText != null) {
      SendMessagesHelper.prepareSendingText(sendingText,did);
    }
    if (contactsToSend != null && !contactsToSend.isEmpty()) {
      for (int a=0; a < contactsToSend.size(); a++) {
        TLRPC.User user=contactsToSend.get(a);
        SendMessagesHelper.getInstance(account).sendMessage(user,did,null,null,null);
      }
    }
  }
  photoPathsArray=null;
  videoPath=null;
  sendingText=null;
  documentsPathsArray=null;
  documentsOriginalPathsArray=null;
  contactsToSend=null;
  contactsToSendUri=null;
}
