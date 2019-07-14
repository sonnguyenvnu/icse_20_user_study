private static void removeEmptyMessages(ArrayList<TLRPC.Message> messages){
  for (int a=0; a < messages.size(); a++) {
    TLRPC.Message message=messages.get(a);
    if (message == null || message instanceof TLRPC.TL_messageEmpty || message.action instanceof TLRPC.TL_messageActionHistoryClear) {
      messages.remove(a);
      a--;
    }
  }
}
