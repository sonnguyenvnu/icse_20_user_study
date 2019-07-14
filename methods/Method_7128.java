private void applyPeerLayer(final TLRPC.EncryptedChat chat,int newPeerLayer){
  int currentPeerLayer=AndroidUtilities.getPeerLayerVersion(chat.layer);
  if (newPeerLayer <= currentPeerLayer) {
    return;
  }
  if (chat.key_hash.length == 16 && currentPeerLayer >= 46) {
    try {
      byte[] sha256=Utilities.computeSHA256(chat.auth_key,0,chat.auth_key.length);
      byte[] key_hash=new byte[36];
      System.arraycopy(chat.key_hash,0,key_hash,0,16);
      System.arraycopy(sha256,0,key_hash,16,20);
      chat.key_hash=key_hash;
      MessagesStorage.getInstance(currentAccount).updateEncryptedChat(chat);
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
  }
  chat.layer=AndroidUtilities.setPeerLayerVersion(chat.layer,newPeerLayer);
  MessagesStorage.getInstance(currentAccount).updateEncryptedChatLayer(chat);
  if (currentPeerLayer < CURRENT_SECRET_CHAT_LAYER) {
    sendNotifyLayerMessage(chat,null);
  }
  AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.encryptedChatUpdated,chat));
}
