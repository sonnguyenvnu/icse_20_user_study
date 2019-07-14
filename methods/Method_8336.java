private void processDone(){
  if (donePressed || nameTextView == null) {
    return;
  }
  if (nameTextView.length() == 0) {
    Vibrator v=(Vibrator)getParentActivity().getSystemService(Context.VIBRATOR_SERVICE);
    if (v != null) {
      v.vibrate(200);
    }
    AndroidUtilities.shakeView(nameTextView,2,0);
    return;
  }
  donePressed=true;
  if (!ChatObject.isChannel(currentChat) && !historyHidden) {
    MessagesController.getInstance(currentAccount).convertToMegaGroup(getParentActivity(),chatId,param -> {
      chatId=param;
      currentChat=MessagesController.getInstance(currentAccount).getChat(param);
      donePressed=false;
      if (info != null) {
        info.hidden_prehistory=true;
      }
      processDone();
    }
);
    return;
  }
  if (info != null) {
    if (ChatObject.isChannel(currentChat) && info.hidden_prehistory != historyHidden) {
      info.hidden_prehistory=historyHidden;
      MessagesController.getInstance(currentAccount).toogleChannelInvitesHistory(chatId,historyHidden);
    }
  }
  if (imageUpdater.uploadingImage != null) {
    createAfterUpload=true;
    progressDialog=new AlertDialog(getParentActivity(),3);
    progressDialog.setOnCancelListener(dialog -> {
      createAfterUpload=false;
      progressDialog=null;
      donePressed=false;
    }
);
    progressDialog.show();
    return;
  }
  if (!currentChat.title.equals(nameTextView.getText().toString())) {
    MessagesController.getInstance(currentAccount).changeChatTitle(chatId,nameTextView.getText().toString());
  }
  String about=info != null && info.about != null ? info.about : "";
  if (descriptionTextView != null && !about.equals(descriptionTextView.getText().toString())) {
    MessagesController.getInstance(currentAccount).updateChatAbout(chatId,descriptionTextView.getText().toString(),info);
  }
  if (signMessages != currentChat.signatures) {
    currentChat.signatures=true;
    MessagesController.getInstance(currentAccount).toogleChannelSignatures(chatId,signMessages);
  }
  if (uploadedAvatar != null) {
    MessagesController.getInstance(currentAccount).changeChatAvatar(chatId,uploadedAvatar,avatar,avatarBig);
  }
 else   if (avatar == null && currentChat.photo instanceof TLRPC.TL_chatPhoto) {
    MessagesController.getInstance(currentAccount).changeChatAvatar(chatId,null,null,null);
  }
  finishFragment();
}
