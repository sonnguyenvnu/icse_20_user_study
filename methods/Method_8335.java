private int getAdminCount(){
  if (info == null) {
    return 1;
  }
  int count=0;
  for (int a=0, N=info.participants.participants.size(); a < N; a++) {
    TLRPC.ChatParticipant chatParticipant=info.participants.participants.get(a);
    if (chatParticipant instanceof TLRPC.TL_chatParticipantAdmin || chatParticipant instanceof TLRPC.TL_chatParticipantCreator) {
      count++;
    }
  }
  return count;
}
