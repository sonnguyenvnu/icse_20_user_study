private void onItemClick(WallpaperCell view,Object object,int index){
  if (actionBar.isActionModeShowed()) {
    if (!(object instanceof TLRPC.TL_wallPaper)) {
      return;
    }
    TLRPC.TL_wallPaper wallPaper=(TLRPC.TL_wallPaper)object;
    if (selectedWallPapers.indexOfKey(wallPaper.id) >= 0) {
      selectedWallPapers.remove(wallPaper.id);
    }
 else {
      selectedWallPapers.put(wallPaper.id,wallPaper);
    }
    if (selectedWallPapers.size() == 0) {
      actionBar.hideActionMode();
    }
 else {
      selectedMessagesCountTextView.setNumber(selectedWallPapers.size(),true);
    }
    scrolling=false;
    view.setChecked(index,selectedWallPapers.indexOfKey(wallPaper.id) >= 0,true);
  }
 else {
    long id=getWallPaperId(object);
    if (object instanceof TLRPC.TL_wallPaper) {
      TLRPC.TL_wallPaper wallPaper=(TLRPC.TL_wallPaper)object;
      if (wallPaper.pattern) {
        object=new ColorWallpaper(wallPaper.id,wallPaper.settings.background_color,wallPaper.id,wallPaper.settings.intensity / 100.0f,wallPaper.settings.motion,null);
      }
    }
    WallpaperActivity wallpaperActivity=new WallpaperActivity(object,null);
    if (currentType == TYPE_COLOR) {
      wallpaperActivity.setDelegate(WallpapersListActivity.this::removeSelfFromStack);
    }
    if (selectedBackground == id) {
      wallpaperActivity.setInitialModes(selectedBackgroundBlurred,selectedBackgroundMotion);
    }
    wallpaperActivity.setPatterns(patterns);
    presentFragment(wallpaperActivity);
  }
}
