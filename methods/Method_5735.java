private int shouldIgnoreCacheForRequest(DataSpec dataSpec){
  if (ignoreCacheOnError && seenCacheError) {
    return CACHE_IGNORED_REASON_ERROR;
  }
 else   if (ignoreCacheForUnsetLengthRequests && dataSpec.length == C.LENGTH_UNSET) {
    return CACHE_IGNORED_REASON_UNSET_LENGTH;
  }
 else {
    return CACHE_NOT_IGNORED;
  }
}
