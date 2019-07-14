private boolean maybeSelectNewPrimaryUrl(){
  List<HlsUrl> variants=masterPlaylist.variants;
  int variantsSize=variants.size();
  long currentTimeMs=SystemClock.elapsedRealtime();
  for (int i=0; i < variantsSize; i++) {
    MediaPlaylistBundle bundle=playlistBundles.get(variants.get(i));
    if (currentTimeMs > bundle.blacklistUntilMs) {
      primaryHlsUrl=bundle.playlistUrl;
      bundle.loadPlaylist();
      return true;
    }
  }
  return false;
}
