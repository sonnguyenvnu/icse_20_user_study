private Intent createIntrnalShortcutIntent(long did){
  Intent shortcutIntent=new Intent(ApplicationLoader.applicationContext,OpenChatReceiver.class);
  int lower_id=(int)did;
  int high_id=(int)(did >> 32);
  if (lower_id == 0) {
    shortcutIntent.putExtra("encId",high_id);
    TLRPC.EncryptedChat encryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat(high_id);
    if (encryptedChat == null) {
      return null;
    }
  }
 else   if (lower_id > 0) {
    shortcutIntent.putExtra("userId",lower_id);
  }
 else   if (lower_id < 0) {
    shortcutIntent.putExtra("chatId",-lower_id);
  }
 else {
    return null;
  }
  shortcutIntent.putExtra("currentAccount",currentAccount);
  shortcutIntent.setAction("com.tmessages.openchat" + did);
  shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
  return shortcutIntent;
}
