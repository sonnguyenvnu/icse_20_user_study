public void putChats(ArrayList<TLRPC.Chat> chats,boolean fromCache){
  if (chats == null || chats.isEmpty()) {
    return;
  }
  int count=chats.size();
  for (int a=0; a < count; a++) {
    TLRPC.Chat chat=chats.get(a);
    putChat(chat,fromCache);
  }
}
