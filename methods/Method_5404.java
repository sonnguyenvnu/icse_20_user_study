@Override public void onLoadCompleted(ParsingLoadable<HlsPlaylist> loadable,long elapsedRealtimeMs,long loadDurationMs){
  HlsPlaylist result=loadable.getResult();
  HlsMasterPlaylist masterPlaylist;
  boolean isMediaPlaylist=result instanceof HlsMediaPlaylist;
  if (isMediaPlaylist) {
    masterPlaylist=HlsMasterPlaylist.createSingleVariantMasterPlaylist(result.baseUri);
  }
 else {
    masterPlaylist=(HlsMasterPlaylist)result;
  }
  this.masterPlaylist=masterPlaylist;
  mediaPlaylistParser=playlistParserFactory.createPlaylistParser(masterPlaylist);
  primaryHlsUrl=masterPlaylist.variants.get(0);
  ArrayList<HlsUrl> urls=new ArrayList<>();
  urls.addAll(masterPlaylist.variants);
  urls.addAll(masterPlaylist.audios);
  urls.addAll(masterPlaylist.subtitles);
  createBundles(urls);
  MediaPlaylistBundle primaryBundle=playlistBundles.get(primaryHlsUrl);
  if (isMediaPlaylist) {
    primaryBundle.processLoadedPlaylist((HlsMediaPlaylist)result,loadDurationMs);
  }
 else {
    primaryBundle.loadPlaylist();
  }
  eventDispatcher.loadCompleted(loadable.dataSpec,loadable.getUri(),loadable.getResponseHeaders(),C.DATA_TYPE_MANIFEST,elapsedRealtimeMs,loadDurationMs,loadable.bytesLoaded());
}
