private void fetchUsersFromChannelInfo(){
  if (currentChat == null || !currentChat.megagroup) {
    return;
  }
  if (chatInfo instanceof TLRPC.TL_channelFull && chatInfo.participants != null) {
    for (int a=0; a < chatInfo.participants.participants.size(); a++) {
      TLRPC.ChatParticipant chatParticipant=chatInfo.participants.participants.get(a);
      participantsMap.put(chatParticipant.user_id,chatParticipant);
    }
  }
}
