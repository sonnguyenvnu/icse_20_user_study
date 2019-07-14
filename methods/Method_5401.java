@Override public void maybeThrowPrimaryPlaylistRefreshError() throws IOException {
  if (initialPlaylistLoader != null) {
    initialPlaylistLoader.maybeThrowError();
  }
  if (primaryHlsUrl != null) {
    maybeThrowPlaylistRefreshError(primaryHlsUrl);
  }
}
