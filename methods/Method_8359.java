private void removeParticipants(TLObject object){
  if (object instanceof TLRPC.ChatParticipant) {
    TLRPC.ChatParticipant chatParticipant=(TLRPC.ChatParticipant)object;
    removeParticipants(chatParticipant.user_id);
  }
 else   if (object instanceof TLRPC.ChannelParticipant) {
    TLRPC.ChannelParticipant channelParticipant=(TLRPC.ChannelParticipant)object;
    removeParticipants(channelParticipant.user_id);
  }
}
