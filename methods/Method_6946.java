public boolean checkCanOpenChat(final Bundle bundle,final BaseFragment fragment,MessageObject originalMessage){
  if (bundle == null || fragment == null) {
    return true;
  }
  TLRPC.User user=null;
  TLRPC.Chat chat=null;
  int user_id=bundle.getInt("user_id",0);
  int chat_id=bundle.getInt("chat_id",0);
  int messageId=bundle.getInt("message_id",0);
  if (user_id != 0) {
    user=getUser(user_id);
  }
 else   if (chat_id != 0) {
    chat=getChat(chat_id);
  }
  if (user == null && chat == null) {
    return true;
  }
  String reason=null;
  if (chat != null) {
    reason=getRestrictionReason(chat.restriction_reason);
  }
 else   if (user != null) {
    reason=getRestrictionReason(user.restriction_reason);
  }
  if (reason != null) {
    showCantOpenAlert(fragment,reason);
    return false;
  }
  if (messageId != 0 && originalMessage != null && chat != null && chat.access_hash == 0) {
    int did=(int)originalMessage.getDialogId();
    if (did != 0) {
      final AlertDialog progressDialog=new AlertDialog(fragment.getParentActivity(),3);
      TLObject req;
      if (did < 0) {
        chat=getChat(-did);
      }
      if (did > 0 || !ChatObject.isChannel(chat)) {
        TLRPC.TL_messages_getMessages request=new TLRPC.TL_messages_getMessages();
        request.id.add(originalMessage.getId());
        req=request;
      }
 else {
        chat=getChat(-did);
        TLRPC.TL_channels_getMessages request=new TLRPC.TL_channels_getMessages();
        request.channel=getInputChannel(chat);
        request.id.add(originalMessage.getId());
        req=request;
      }
      final int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
        if (response != null) {
          AndroidUtilities.runOnUIThread(() -> {
            try {
              progressDialog.dismiss();
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
            TLRPC.messages_Messages res=(TLRPC.messages_Messages)response;
            putUsers(res.users,false);
            putChats(res.chats,false);
            MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,true,true);
            fragment.presentFragment(new ChatActivity(bundle),true);
          }
);
        }
      }
);
      progressDialog.setOnCancelListener(dialog -> {
        ConnectionsManager.getInstance(currentAccount).cancelRequest(reqId,true);
        if (fragment != null) {
          fragment.setVisibleDialog(null);
        }
      }
);
      fragment.setVisibleDialog(progressDialog);
      progressDialog.show();
      return false;
    }
  }
  return true;
}
