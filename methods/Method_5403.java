@Override public void refreshPlaylist(HlsUrl url){
  playlistBundles.get(url).loadPlaylist();
}
