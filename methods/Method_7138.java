public void acceptSecretChat(final TLRPC.EncryptedChat encryptedChat){
  if (acceptingChats.get(encryptedChat.id) != null) {
    return;
  }
  acceptingChats.put(encryptedChat.id,encryptedChat);
  TLRPC.TL_messages_getDhConfig req=new TLRPC.TL_messages_getDhConfig();
  req.random_length=256;
  req.version=MessagesStorage.getInstance(currentAccount).getLastSecretVersion();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      TLRPC.messages_DhConfig res=(TLRPC.messages_DhConfig)response;
      if (response instanceof TLRPC.TL_messages_dhConfig) {
        if (!Utilities.isGoodPrime(res.p,res.g)) {
          acceptingChats.remove(encryptedChat.id);
          declineSecretChat(encryptedChat.id);
          return;
        }
        MessagesStorage.getInstance(currentAccount).setSecretPBytes(res.p);
        MessagesStorage.getInstance(currentAccount).setSecretG(res.g);
        MessagesStorage.getInstance(currentAccount).setLastSecretVersion(res.version);
        MessagesStorage.getInstance(currentAccount).saveSecretParams(MessagesStorage.getInstance(currentAccount).getLastSecretVersion(),MessagesStorage.getInstance(currentAccount).getSecretG(),MessagesStorage.getInstance(currentAccount).getSecretPBytes());
      }
      byte[] salt=new byte[256];
      for (int a=0; a < 256; a++) {
        salt[a]=(byte)((byte)(Utilities.random.nextDouble() * 256) ^ res.random[a]);
      }
      encryptedChat.a_or_b=salt;
      encryptedChat.seq_in=-1;
      encryptedChat.seq_out=0;
      BigInteger p=new BigInteger(1,MessagesStorage.getInstance(currentAccount).getSecretPBytes());
      BigInteger g_b=BigInteger.valueOf(MessagesStorage.getInstance(currentAccount).getSecretG());
      g_b=g_b.modPow(new BigInteger(1,salt),p);
      BigInteger g_a=new BigInteger(1,encryptedChat.g_a);
      if (!Utilities.isGoodGaAndGb(g_a,p)) {
        acceptingChats.remove(encryptedChat.id);
        declineSecretChat(encryptedChat.id);
        return;
      }
      byte[] g_b_bytes=g_b.toByteArray();
      if (g_b_bytes.length > 256) {
        byte[] correctedAuth=new byte[256];
        System.arraycopy(g_b_bytes,1,correctedAuth,0,256);
        g_b_bytes=correctedAuth;
      }
      g_a=g_a.modPow(new BigInteger(1,salt),p);
      byte[] authKey=g_a.toByteArray();
      if (authKey.length > 256) {
        byte[] correctedAuth=new byte[256];
        System.arraycopy(authKey,authKey.length - 256,correctedAuth,0,256);
        authKey=correctedAuth;
      }
 else       if (authKey.length < 256) {
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
      encryptedChat.auth_key=authKey;
      encryptedChat.key_create_date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
      TLRPC.TL_messages_acceptEncryption req2=new TLRPC.TL_messages_acceptEncryption();
      req2.g_b=g_b_bytes;
      req2.peer=new TLRPC.TL_inputEncryptedChat();
      req2.peer.chat_id=encryptedChat.id;
      req2.peer.access_hash=encryptedChat.access_hash;
      req2.key_fingerprint=Utilities.bytesToLong(authKeyId);
      ConnectionsManager.getInstance(currentAccount).sendRequest(req2,(response1,error1) -> {
        acceptingChats.remove(encryptedChat.id);
        if (error1 == null) {
          final TLRPC.EncryptedChat newChat=(TLRPC.EncryptedChat)response1;
          newChat.auth_key=encryptedChat.auth_key;
          newChat.user_id=encryptedChat.user_id;
          newChat.seq_in=encryptedChat.seq_in;
          newChat.seq_out=encryptedChat.seq_out;
          newChat.key_create_date=encryptedChat.key_create_date;
          newChat.key_use_count_in=encryptedChat.key_use_count_in;
          newChat.key_use_count_out=encryptedChat.key_use_count_out;
          MessagesStorage.getInstance(currentAccount).updateEncryptedChat(newChat);
          MessagesController.getInstance(currentAccount).putEncryptedChat(newChat,false);
          AndroidUtilities.runOnUIThread(() -> {
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.encryptedChatUpdated,newChat);
            sendNotifyLayerMessage(newChat,null);
          }
);
        }
      }
);
    }
 else {
      acceptingChats.remove(encryptedChat.id);
    }
  }
);
}
