protected void removeDeletedMessagesFromArray(final long dialog_id,ArrayList<TLRPC.Message> messages){
  int maxDeletedId=deletedHistory.get(dialog_id,0);
  if (maxDeletedId == 0) {
    return;
  }
  for (int a=0, N=messages.size(); a < N; a++) {
    TLRPC.Message message=messages.get(a);
    if (message.id <= maxDeletedId) {
      messages.remove(a);
      a--;
      N--;
    }
  }
}
