public void setEncryptedChat(TLRPC.EncryptedChat encryptedChat){
  data=encryptedChat.key_hash;
  if (data == null) {
    encryptedChat.key_hash=data=AndroidUtilities.calcAuthKeyHash(encryptedChat.auth_key);
  }
  invalidateSelf();
}
