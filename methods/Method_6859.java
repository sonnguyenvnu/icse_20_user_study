public void loadChannelParticipants(final Integer chat_id){
  if (loadingFullParticipants.contains(chat_id) || loadedFullParticipants.contains(chat_id)) {
    return;
  }
  loadingFullParticipants.add(chat_id);
  final TLRPC.TL_channels_getParticipants req=new TLRPC.TL_channels_getParticipants();
  req.channel=getInputChannel(chat_id);
  req.filter=new TLRPC.TL_channelParticipantsRecent();
  req.offset=0;
  req.limit=32;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      TLRPC.TL_channels_channelParticipants res=(TLRPC.TL_channels_channelParticipants)response;
      putUsers(res.users,false);
      MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,null,true,true);
      MessagesStorage.getInstance(currentAccount).updateChannelUsers(chat_id,res.participants);
      loadedFullParticipants.add(chat_id);
    }
    loadingFullParticipants.remove(chat_id);
  }
));
}
