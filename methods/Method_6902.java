public void addUsersToChannel(int chat_id,ArrayList<TLRPC.InputUser> users,final BaseFragment fragment){
  if (users == null || users.isEmpty()) {
    return;
  }
  final TLRPC.TL_channels_inviteToChannel req=new TLRPC.TL_channels_inviteToChannel();
  req.channel=getInputChannel(chat_id);
  req.users=users;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error != null) {
      AndroidUtilities.runOnUIThread(() -> AlertsCreator.processError(currentAccount,error,fragment,req,true));
      return;
    }
    processUpdates((TLRPC.Updates)response,false);
  }
);
}
