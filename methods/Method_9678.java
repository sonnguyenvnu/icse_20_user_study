@Override public boolean onFragmentCreate(){
  if (currentType == TYPE_ALL) {
    NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.wallpapersDidLoad);
    NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.didSetNewWallpapper);
    NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.wallpapersNeedReload);
    MessagesStorage.getInstance(currentAccount).getWallpapers();
  }
 else {
    for (int a=0; a < defaultColors.length; a++) {
      wallPapers.add(new ColorWallpaper(-(a + 3),defaultColors[a]));
    }
    if (currentType == TYPE_COLOR && patterns.isEmpty()) {
      NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.wallpapersDidLoad);
      MessagesStorage.getInstance(currentAccount).getWallpapers();
    }
  }
  return super.onFragmentCreate();
}
