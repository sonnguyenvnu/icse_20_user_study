@SuppressWarnings("unchecked") @Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.wallpapersDidLoad) {
    ArrayList<TLRPC.TL_wallPaper> arrayList=(ArrayList<TLRPC.TL_wallPaper>)args[0];
    patterns.clear();
    if (currentType != TYPE_COLOR) {
      wallPapers.clear();
      allWallPapers.clear();
      allWallPapersDict.clear();
      allWallPapers.addAll(arrayList);
    }
    for (int a=0, N=arrayList.size(); a < N; a++) {
      TLRPC.TL_wallPaper wallPaper=arrayList.get(a);
      if (wallPaper.pattern) {
        patterns.add(wallPaper);
      }
      if (currentType != TYPE_COLOR && (!wallPaper.pattern || wallPaper.settings != null)) {
        allWallPapersDict.put(wallPaper.id,wallPaper);
        wallPapers.add(wallPaper);
      }
    }
    selectedBackground=Theme.getSelectedBackgroundId();
    fillWallpapersWithCustom();
    loadWallpapers();
  }
 else   if (id == NotificationCenter.didSetNewWallpapper) {
    if (listView != null) {
      listView.invalidateViews();
    }
    if (actionBar != null) {
      actionBar.closeSearchField();
    }
  }
 else   if (id == NotificationCenter.wallpapersNeedReload) {
    MessagesStorage.getInstance(currentAccount).getWallpapers();
  }
}
