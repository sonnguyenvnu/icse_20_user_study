public void openByUserName(String username,final BaseFragment fragment,final int type){
  if (username == null || fragment == null) {
    return;
  }
  TLObject object=getUserOrChat(username);
  TLRPC.User user=null;
  TLRPC.Chat chat=null;
  if (object instanceof TLRPC.User) {
    user=(TLRPC.User)object;
    if (user.min) {
      user=null;
    }
  }
 else   if (object instanceof TLRPC.Chat) {
    chat=(TLRPC.Chat)object;
    if (chat.min) {
      chat=null;
    }
  }
  if (user != null) {
    openChatOrProfileWith(user,null,fragment,type,false);
  }
 else   if (chat != null) {
    openChatOrProfileWith(null,chat,fragment,1,false);
  }
 else {
    if (fragment.getParentActivity() == null) {
      return;
    }
    final AlertDialog[] progressDialog=new AlertDialog[]{new AlertDialog(fragment.getParentActivity(),3)};
    TLRPC.TL_contacts_resolveUsername req=new TLRPC.TL_contacts_resolveUsername();
    req.username=username;
    final int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      try {
        progressDialog[0].dismiss();
      }
 catch (      Exception ignored) {
      }
      progressDialog[0]=null;
      fragment.setVisibleDialog(null);
      if (error == null) {
        TLRPC.TL_contacts_resolvedPeer res=(TLRPC.TL_contacts_resolvedPeer)response;
        putUsers(res.users,false);
        putChats(res.chats,false);
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,false,true);
        if (!res.chats.isEmpty()) {
          openChatOrProfileWith(null,res.chats.get(0),fragment,1,false);
        }
 else         if (!res.users.isEmpty()) {
          openChatOrProfileWith(res.users.get(0),null,fragment,type,false);
        }
      }
 else {
        if (fragment != null && fragment.getParentActivity() != null) {
          try {
            Toast.makeText(fragment.getParentActivity(),LocaleController.getString("NoUsernameFound",R.string.NoUsernameFound),Toast.LENGTH_SHORT).show();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
      }
    }
));
    AndroidUtilities.runOnUIThread(() -> {
      if (progressDialog[0] == null) {
        return;
      }
      progressDialog[0].setOnCancelListener(dialog -> ConnectionsManager.getInstance(currentAccount).cancelRequest(reqId,true));
      fragment.showDialog(progressDialog[0]);
    }
,500);
  }
}
