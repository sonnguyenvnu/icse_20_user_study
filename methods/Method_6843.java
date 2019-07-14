public void setUserAdminRole(final int chatId,TLRPC.User user,TLRPC.TL_chatAdminRights rights,final boolean isChannel,final BaseFragment parentFragment,boolean addingNew){
  if (user == null || rights == null) {
    return;
  }
  TLRPC.Chat chat=getChat(chatId);
  if (ChatObject.isChannel(chat)) {
    final TLRPC.TL_channels_editAdmin req=new TLRPC.TL_channels_editAdmin();
    req.channel=getInputChannel(chat);
    req.user_id=getInputUser(user);
    req.admin_rights=rights;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (error == null) {
        processUpdates((TLRPC.Updates)response,false);
        AndroidUtilities.runOnUIThread(() -> loadFullChat(chatId,0,true),1000);
      }
 else {
        AndroidUtilities.runOnUIThread(() -> AlertsCreator.processError(currentAccount,error,parentFragment,req,isChannel));
      }
    }
);
  }
 else {
    TLRPC.TL_messages_editChatAdmin req=new TLRPC.TL_messages_editChatAdmin();
    req.chat_id=chatId;
    req.user_id=getInputUser(user);
    req.is_admin=rights.change_info || rights.delete_messages || rights.ban_users || rights.invite_users || rights.pin_messages || rights.add_admins;
    RequestDelegate requestDelegate=(response,error) -> {
      if (error == null) {
        AndroidUtilities.runOnUIThread(() -> loadFullChat(chatId,0,true),1000);
      }
 else {
        AndroidUtilities.runOnUIThread(() -> AlertsCreator.processError(currentAccount,error,parentFragment,req,false));
      }
    }
;
    if (req.is_admin && addingNew) {
      addUserToChat(chatId,user,null,0,null,parentFragment,() -> ConnectionsManager.getInstance(currentAccount).sendRequest(req,requestDelegate));
    }
 else {
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,requestDelegate);
    }
  }
}
