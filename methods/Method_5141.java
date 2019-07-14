@Override public void maybeThrowError() throws IOException {
  loader.maybeThrowError();
  if (!loader.isLoading()) {
    chunkSource.maybeThrowError();
  }
}
