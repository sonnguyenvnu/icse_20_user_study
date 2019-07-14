private void recycleAdapter(RecyclerView.Adapter adapter){
  if (adapter instanceof SharedPhotoVideoAdapter) {
    cellCache.addAll(cache);
    cache.clear();
  }
 else   if (adapter == audioAdapter) {
    audioCellCache.addAll(audioCache);
    audioCache.clear();
  }
}
