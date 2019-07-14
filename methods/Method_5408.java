private void maybeSetPrimaryUrl(HlsUrl url){
  if (url == primaryHlsUrl || !masterPlaylist.variants.contains(url) || (primaryUrlSnapshot != null && primaryUrlSnapshot.hasEndTag)) {
    return;
  }
  primaryHlsUrl=url;
  playlistBundles.get(primaryHlsUrl).loadPlaylist();
}
