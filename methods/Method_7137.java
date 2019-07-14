public void declineSecretChat(int chat_id){
  TLRPC.TL_messages_discardEncryption req=new TLRPC.TL_messages_discardEncryption();
  req.chat_id=chat_id;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
  }
);
}
