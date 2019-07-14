private void getChannelParticipants(boolean reload){
  if (loadingUsers || participantsMap == null || chatInfo == null) {
    return;
  }
  loadingUsers=true;
  final int delay=participantsMap.size() != 0 && reload ? 300 : 0;
  final TLRPC.TL_channels_getParticipants req=new TLRPC.TL_channels_getParticipants();
  req.channel=MessagesController.getInstance(currentAccount).getInputChannel(chat_id);
  req.filter=new TLRPC.TL_channelParticipantsRecent();
  req.offset=reload ? 0 : participantsMap.size();
  req.limit=200;
  int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      TLRPC.TL_channels_channelParticipants res=(TLRPC.TL_channels_channelParticipants)response;
      MessagesController.getInstance(currentAccount).putUsers(res.users,false);
      if (res.users.size() < 200) {
        usersEndReached=true;
      }
      if (req.offset == 0) {
        participantsMap.clear();
        chatInfo.participants=new TLRPC.TL_chatParticipants();
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,null,true,true);
        MessagesStorage.getInstance(currentAccount).updateChannelUsers(chat_id,res.participants);
      }
      for (int a=0; a < res.participants.size(); a++) {
        TLRPC.TL_chatChannelParticipant participant=new TLRPC.TL_chatChannelParticipant();
        participant.channelParticipant=res.participants.get(a);
        participant.inviter_id=participant.channelParticipant.inviter_id;
        participant.user_id=participant.channelParticipant.user_id;
        participant.date=participant.channelParticipant.date;
        if (participantsMap.indexOfKey(participant.user_id) < 0) {
          chatInfo.participants.participants.add(participant);
          participantsMap.put(participant.user_id,participant);
        }
      }
    }
    updateOnlineCount();
    loadingUsers=false;
    updateRowsIds();
    if (listAdapter != null) {
      listAdapter.notifyDataSetChanged();
    }
  }
,delay));
  ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
}
