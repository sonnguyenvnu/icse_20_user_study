public void setUserBannedRole(final int chatId,TLRPC.User user,TLRPC.TL_chatBannedRights rights,final boolean isChannel,final BaseFragment parentFragment){
  if (user == null || rights == null) {
    return;
  }
  final TLRPC.TL_channels_editBanned req=new TLRPC.TL_channels_editBanned();
  req.channel=getInputChannel(chatId);
  req.user_id=getInputUser(user);
  req.banned_rights=rights;
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
