public void loadBotInfo(final int uid,boolean cache,final int classGuid){
  if (cache) {
    TLRPC.BotInfo botInfo=botInfos.get(uid);
    if (botInfo != null) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.botInfoDidLoad,botInfo,classGuid);
      return;
    }
  }
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      TLRPC.BotInfo botInfo=null;
      SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT info FROM bot_info WHERE uid = %d",uid));
      if (cursor.next()) {
        NativeByteBuffer data;
        if (!cursor.isNull(0)) {
          data=cursor.byteBufferValue(0);
          if (data != null) {
            botInfo=TLRPC.BotInfo.TLdeserialize(data,data.readInt32(false),false);
            data.reuse();
          }
        }
      }
      cursor.dispose();
      if (botInfo != null) {
        final TLRPC.BotInfo botInfoFinal=botInfo;
        AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.botInfoDidLoad,botInfoFinal,classGuid));
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
