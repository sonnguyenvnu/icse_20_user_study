public void addRecentGif(TLRPC.Document document,int date){
  boolean found=false;
  for (int a=0; a < recentGifs.size(); a++) {
    TLRPC.Document image=recentGifs.get(a);
    if (image.id == document.id) {
      recentGifs.remove(a);
      recentGifs.add(0,image);
      found=true;
      break;
    }
  }
  if (!found) {
    recentGifs.add(0,document);
  }
  if (recentGifs.size() > MessagesController.getInstance(currentAccount).maxRecentGifsCount) {
    final TLRPC.Document old=recentGifs.remove(recentGifs.size() - 1);
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
      try {
        MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("DELETE FROM web_recent_v3 WHERE id = '" + old.id + "' AND type = 2").stepThis().dispose();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
);
  }
  ArrayList<TLRPC.Document> arrayList=new ArrayList<>();
  arrayList.add(document);
  processLoadedRecentDocuments(0,arrayList,true,date,false);
}
