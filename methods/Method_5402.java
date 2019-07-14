@Override public void maybeThrowPlaylistRefreshError(HlsUrl url) throws IOException {
  playlistBundles.get(url).maybeThrowPlaylistRefreshError();
}
