@Override public long open(DataSpec dataSpec) throws IOException {
  try {
    key=cacheKeyFactory.buildCacheKey(dataSpec);
    uri=dataSpec.uri;
    actualUri=getRedirectedUriOrDefault(cache,key,uri);
    httpMethod=dataSpec.httpMethod;
    flags=dataSpec.flags;
    readPosition=dataSpec.position;
    int reason=shouldIgnoreCacheForRequest(dataSpec);
    currentRequestIgnoresCache=reason != CACHE_NOT_IGNORED;
    if (currentRequestIgnoresCache) {
      notifyCacheIgnored(reason);
    }
    if (dataSpec.length != C.LENGTH_UNSET || currentRequestIgnoresCache) {
      bytesRemaining=dataSpec.length;
    }
 else {
      bytesRemaining=ContentMetadata.getContentLength(cache.getContentMetadata(key));
      if (bytesRemaining != C.LENGTH_UNSET) {
        bytesRemaining-=dataSpec.position;
        if (bytesRemaining <= 0) {
          throw new DataSourceException(DataSourceException.POSITION_OUT_OF_RANGE);
        }
      }
    }
    openNextSource(false);
    return bytesRemaining;
  }
 catch (  IOException e) {
    handleBeforeThrow(e);
    throw e;
  }
}
