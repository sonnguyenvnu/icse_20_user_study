public void requestNewSecretChatKey(final TLRPC.EncryptedChat encryptedChat){
  if (AndroidUtilities.getPeerLayerVersion(encryptedChat.layer) < 20) {
    return;
  }
  final byte[] salt=new byte[256];
  Utilities.random.nextBytes(salt);
  BigInteger i_g_a=BigInteger.valueOf(MessagesStorage.getInstance(currentAccount).getSecretG());
  i_g_a=i_g_a.modPow(new BigInteger(1,salt),new BigInteger(1,MessagesStorage.getInstance(currentAccount).getSecretPBytes()));
  byte[] g_a=i_g_a.toByteArray();
  if (g_a.length > 256) {
    byte[] correctedAuth=new byte[256];
    System.arraycopy(g_a,1,correctedAuth,0,256);
    g_a=correctedAuth;
  }
  encryptedChat.exchange_id=SendMessagesHelper.getInstance(currentAccount).getNextRandomId();
  encryptedChat.a_or_b=salt;
  encryptedChat.g_a=g_a;
  MessagesStorage.getInstance(currentAccount).updateEncryptedChat(encryptedChat);
  sendRequestKeyMessage(encryptedChat,null);
}
