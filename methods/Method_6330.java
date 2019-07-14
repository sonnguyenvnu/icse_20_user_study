public void checkStickers(int type){
  if (!loadingStickers[type] && (!stickersLoaded[type] || Math.abs(System.currentTimeMillis() / 1000 - loadDate[type]) >= 60 * 60)) {
    loadStickers(type,true,false);
  }
}
