public void putEncryptedChats(ArrayList<TLRPC.EncryptedChat> encryptedChats,boolean fromCache){
  if (encryptedChats == null || encryptedChats.isEmpty()) {
    return;
  }
  int count=encryptedChats.size();
  for (int a=0; a < count; a++) {
    TLRPC.EncryptedChat encryptedChat=encryptedChats.get(a);
    putEncryptedChat(encryptedChat,fromCache);
  }
}
