public void markMessageAsRead(final long dialog_id,final long random_id,int ttl){
  if (random_id == 0 || dialog_id == 0 || ttl <= 0 && ttl != Integer.MIN_VALUE) {
    return;
  }
  int lower_part=(int)dialog_id;
  int high_id=(int)(dialog_id >> 32);
  if (lower_part != 0) {
    return;
  }
  TLRPC.EncryptedChat chat=getEncryptedChat(high_id);
  if (chat == null) {
    return;
  }
  ArrayList<Long> random_ids=new ArrayList<>();
  random_ids.add(random_id);
  SecretChatHelper.getInstance(currentAccount).sendMessagesReadMessage(chat,random_ids,null);
  if (ttl > 0) {
    int time=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
    MessagesStorage.getInstance(currentAccount).createTaskForSecretChat(chat.id,time,time,0,random_ids);
  }
}
