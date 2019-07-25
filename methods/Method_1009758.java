private void refresh(){
  final BitmapPool pool=glide.getBitmapPool();
  final String total=getSizeString(pool.getMaxSize());
  final String memCacheCurrent=getSizeString(memoryCache.getCurrentSize());
  final String memCacheMax=getSizeString(memoryCache.getMaxSize());
  poolSizeLabel.setText(total);
  memCacheCurrentLabel.setText(memCacheCurrent);
  memCacheMaxLabel.setText(memCacheMax);
}
