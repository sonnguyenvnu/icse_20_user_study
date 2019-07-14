public void putEncryptedChat(TLRPC.EncryptedChat encryptedChat,boolean fromCache){
  if (encryptedChat == null) {
    return;
  }
  if (fromCache) {
    encryptedChats.putIfAbsent(encryptedChat.id,encryptedChat);
  }
 else {
    encryptedChats.put(encryptedChat.id,encryptedChat);
  }
}
