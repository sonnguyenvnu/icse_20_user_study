public void addRecentSticker(final int type,Object parentObject,TLRPC.Document document,int date,boolean remove){
  boolean found=false;
  for (int a=0; a < recentStickers[type].size(); a++) {
    TLRPC.Document image=recentStickers[type].get(a);
    if (image.id == document.id) {
      recentStickers[type].remove(a);
      if (!remove) {
        recentStickers[type].add(0,image);
      }
      found=true;
      break;
    }
  }
  if (!found && !remove) {
    recentStickers[type].add(0,document);
  }
  int maxCount;
  if (type == TYPE_FAVE) {
    if (remove) {
      Toast.makeText(ApplicationLoader.applicationContext,LocaleController.getString("RemovedFromFavorites",R.string.RemovedFromFavorites),Toast.LENGTH_SHORT).show();
    }
 else {
      Toast.makeText(ApplicationLoader.applicationContext,LocaleController.getString("AddedToFavorites",R.string.AddedToFavorites),Toast.LENGTH_SHORT).show();
    }
    TLRPC.TL_messages_faveSticker req=new TLRPC.TL_messages_faveSticker();
    req.id=new TLRPC.TL_inputDocument();
    req.id.id=document.id;
    req.id.access_hash=document.access_hash;
    req.id.file_reference=document.file_reference;
    if (req.id.file_reference == null) {
      req.id.file_reference=new byte[0];
    }
    req.unfave=remove;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (error != null && FileRefController.isFileRefError(error.text) && parentObject != null) {
        FileRefController.getInstance(currentAccount).requestReference(parentObject,req);
      }
    }
);
    maxCount=MessagesController.getInstance(currentAccount).maxFaveStickersCount;
  }
 else {
    maxCount=MessagesController.getInstance(currentAccount).maxRecentStickersCount;
  }
  if (recentStickers[type].size() > maxCount || remove) {
    final TLRPC.Document old=remove ? document : recentStickers[type].remove(recentStickers[type].size() - 1);
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
      int cacheType;
      if (type == TYPE_IMAGE) {
        cacheType=3;
      }
 else       if (type == TYPE_MASK) {
        cacheType=4;
      }
 else {
        cacheType=5;
      }
      try {
        MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("DELETE FROM web_recent_v3 WHERE id = '" + old.id + "' AND type = " + cacheType).stepThis().dispose();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
);
  }
  if (!remove) {
    ArrayList<TLRPC.Document> arrayList=new ArrayList<>();
    arrayList.add(document);
    processLoadedRecentDocuments(type,arrayList,false,date,false);
  }
  if (type == TYPE_FAVE) {
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.recentDocumentsDidLoad,false,type);
  }
}
