@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.wallpapersNeedReload) {
    if (currentWallpaper instanceof WallpapersListActivity.FileWallpaper) {
      WallpapersListActivity.FileWallpaper fileWallpaper=(WallpapersListActivity.FileWallpaper)currentWallpaper;
      if (fileWallpaper.id == -1) {
        fileWallpaper.id=(Long)args[0];
      }
    }
  }
}
