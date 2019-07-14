public void checkFeaturedStickers(){
  if (!loadingFeaturedStickers && (!featuredStickersLoaded || Math.abs(System.currentTimeMillis() / 1000 - loadFeaturedDate) >= 60 * 60)) {
    loadFeaturedStickers(true,false);
  }
}
