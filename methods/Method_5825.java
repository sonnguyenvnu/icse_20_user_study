@Override public long open(DataSpec dataSpec) throws IOException {
  lastOpenedUri=dataSpec.uri;
  lastResponseHeaders=Collections.emptyMap();
  long availableBytes=dataSource.open(dataSpec);
  lastOpenedUri=Assertions.checkNotNull(getUri());
  lastResponseHeaders=getResponseHeaders();
  return availableBytes;
}
