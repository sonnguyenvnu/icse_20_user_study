public void loadArchivedStickersCount(final int type,boolean cache){
  if (cache) {
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    int count=preferences.getInt("archivedStickersCount" + type,-1);
    if (count == -1) {
      loadArchivedStickersCount(type,false);
    }
 else {
      archivedStickersCount[type]=count;
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.archivedStickersCountDidLoad,type);
    }
  }
 else {
    TLRPC.TL_messages_getArchivedStickers req=new TLRPC.TL_messages_getArchivedStickers();
    req.limit=0;
    req.masks=type == TYPE_MASK;
    int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      if (error == null) {
        TLRPC.TL_messages_archivedStickers res=(TLRPC.TL_messages_archivedStickers)response;
        archivedStickersCount[type]=res.count;
        SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
        preferences.edit().putInt("archivedStickersCount" + type,res.count).commit();
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.archivedStickersCountDidLoad,type);
      }
    }
));
  }
}
