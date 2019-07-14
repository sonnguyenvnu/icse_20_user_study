private void addCanBanUser(Bundle bundle,int uid){
  if (!currentChat.megagroup || admins == null || !ChatObject.canBlockUsers(currentChat)) {
    return;
  }
  for (int a=0; a < admins.size(); a++) {
    TLRPC.ChannelParticipant channelParticipant=admins.get(a);
    if (channelParticipant.user_id == uid) {
      if (!channelParticipant.can_edit) {
        return;
      }
      break;
    }
  }
  bundle.putInt("ban_chat_id",currentChat.id);
}
