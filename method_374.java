@Override public InputStream _XXXXX_(CacheRequest cacheRequest) throws IOException {
  return new UnknownLengthHttpInputStream(stream.getInputStream(),cacheRequest,httpEngine);
}