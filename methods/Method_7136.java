public void processAcceptedSecretChat(final TLRPC.EncryptedChat encryptedChat){
  BigInteger p=new BigInteger(1,MessagesStorage.getInstance(currentAccount).getSecretPBytes());
  BigInteger i_authKey=new BigInteger(1,encryptedChat.g_a_or_b);
  if (!Utilities.isGoodGaAndGb(i_authKey,p)) {
    declineSecretChat(encryptedChat.id);
    return;
  }
  i_authKey=i_authKey.modPow(new BigInteger(1,encryptedChat.a_or_b),p);
  byte[] authKey=i_authKey.toByteArray();
  if (authKey.length > 256) {
    byte[] correctedAuth=new byte[256];
    System.arraycopy(authKey,authKey.length - 256,correctedAuth,0,256);
    authKey=correctedAuth;
  }
 else   if (authKey.length < 256) {
    byte[] correctedAuth=new byte[256];
    System.arraycopy(authKey,0,correctedAuth,256 - authKey.length,authKey.length);
    for (int a=0; a < 256 - authKey.length; a++) {
      correctedAuth[a]=0;
    }
    authKey=correctedAuth;
  }
  byte[] authKeyHash=Utilities.computeSHA1(authKey);
  byte[] authKeyId=new byte[8];
  System.arraycopy(authKeyHash,authKeyHash.length - 8,authKeyId,0,8);
  long fingerprint=Utilities.bytesToLong(authKeyId);
  if (encryptedChat.key_fingerprint == fingerprint) {
    encryptedChat.auth_key=authKey;
    encryptedChat.key_create_date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
    encryptedChat.seq_in=-2;
    encryptedChat.seq_out=1;
    MessagesStorage.getInstance(currentAccount).updateEncryptedChat(encryptedChat);
    MessagesController.getInstance(currentAccount).putEncryptedChat(encryptedChat,false);
    AndroidUtilities.runOnUIThread(() -> {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.encryptedChatUpdated,encryptedChat);
      sendNotifyLayerMessage(encryptedChat,null);
    }
);
  }
 else {
    final TLRPC.TL_encryptedChatDiscarded newChat=new TLRPC.TL_encryptedChatDiscarded();
    newChat.id=encryptedChat.id;
    newChat.user_id=encryptedChat.user_id;
    newChat.auth_key=encryptedChat.auth_key;
    newChat.key_create_date=encryptedChat.key_create_date;
    newChat.key_use_count_in=encryptedChat.key_use_count_in;
    newChat.key_use_count_out=encryptedChat.key_use_count_out;
    newChat.seq_in=encryptedChat.seq_in;
    newChat.seq_out=encryptedChat.seq_out;
    newChat.admin_id=encryptedChat.admin_id;
    newChat.mtproto_seq=encryptedChat.mtproto_seq;
    MessagesStorage.getInstance(currentAccount).updateEncryptedChat(newChat);
    AndroidUtilities.runOnUIThread(() -> {
      MessagesController.getInstance(currentAccount).putEncryptedChat(newChat,false);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.encryptedChatUpdated,newChat);
    }
);
    declineSecretChat(encryptedChat.id);
  }
}
