private void linkChat(TLRPC.Chat chat,BaseFragment createFragment){
  if (chat == null) {
    return;
  }
  if (!ChatObject.isChannel(chat)) {
    MessagesController.getInstance(currentAccount).convertToMegaGroup(getParentActivity(),chat.id,param -> {
      MessagesController.getInstance(currentAccount).toogleChannelInvitesHistory(param,false);
      linkChat(getMessagesController().getChat(param),createFragment);
    }
);
    return;
  }
  final AlertDialog[] progressDialog=new AlertDialog[]{createFragment != null ? null : new AlertDialog(getParentActivity(),3)};
  TLRPC.TL_channels_setDiscussionGroup req=new TLRPC.TL_channels_setDiscussionGroup();
  req.broadcast=MessagesController.getInputChannel(currentChat);
  req.group=MessagesController.getInputChannel(chat);
  int requestId=getConnectionsManager().sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (progressDialog[0] != null) {
      try {
        progressDialog[0].dismiss();
      }
 catch (      Throwable ignore) {
      }
      progressDialog[0]=null;
    }
    info.linked_chat_id=chat.id;
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoad,info,0,false,null);
    getMessagesController().loadFullChat(currentChatId,0,true);
    if (createFragment != null) {
      removeSelfFromStack();
      createFragment.finishFragment();
    }
 else {
      finishFragment();
    }
  }
));
  AndroidUtilities.runOnUIThread(() -> {
    if (progressDialog[0] == null) {
      return;
    }
    progressDialog[0].setOnCancelListener(dialog -> ConnectionsManager.getInstance(currentAccount).cancelRequest(requestId,true));
    showDialog(progressDialog[0]);
  }
,500);
}
