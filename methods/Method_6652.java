private void checkScreenshots(ArrayList<Long> dates){
  if (dates == null || dates.isEmpty() || lastChatEnterTime == 0 || (lastUser == null && !(lastSecretChat instanceof TLRPC.TL_encryptedChat))) {
    return;
  }
  long dt=2000;
  boolean send=false;
  for (int a=0; a < dates.size(); a++) {
    Long date=dates.get(a);
    if (lastMediaCheckTime != 0 && date <= lastMediaCheckTime) {
      continue;
    }
    if (date >= lastChatEnterTime) {
      if (lastChatLeaveTime == 0 || date <= lastChatLeaveTime + dt) {
        lastMediaCheckTime=Math.max(lastMediaCheckTime,date);
        send=true;
      }
    }
  }
  if (send) {
    if (lastSecretChat != null) {
      SecretChatHelper.getInstance(lastChatAccount).sendScreenshotMessage(lastSecretChat,lastChatVisibleMessages,null);
    }
 else {
      SendMessagesHelper.getInstance(lastChatAccount).sendScreenshotMessage(lastUser,lastMessageId,null);
    }
  }
}
