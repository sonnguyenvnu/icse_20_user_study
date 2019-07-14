private void createBundles(List<HlsUrl> urls){
  int listSize=urls.size();
  for (int i=0; i < listSize; i++) {
    HlsUrl url=urls.get(i);
    MediaPlaylistBundle bundle=new MediaPlaylistBundle(url);
    playlistBundles.put(url,bundle);
  }
}
