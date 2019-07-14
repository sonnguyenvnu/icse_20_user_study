private void loadAdmins(){
  TLRPC.TL_channels_getParticipants req=new TLRPC.TL_channels_getParticipants();
  req.channel=MessagesController.getInputChannel(currentChat);
  req.filter=new TLRPC.TL_channelParticipantsAdmins();
  req.offset=0;
  req.limit=200;
  int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      TLRPC.TL_channels_channelParticipants res=(TLRPC.TL_channels_channelParticipants)response;
      MessagesController.getInstance(currentAccount).putUsers(res.users,false);
      admins=res.participants;
      if (visibleDialog instanceof AdminLogFilterAlert) {
        ((AdminLogFilterAlert)visibleDialog).setCurrentAdmins(admins);
      }
    }
  }
));
  ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
}
