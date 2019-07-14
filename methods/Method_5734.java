private void handleBeforeThrow(IOException exception){
  if (isReadingFromCache() || exception instanceof CacheException) {
    seenCacheError=true;
  }
}
