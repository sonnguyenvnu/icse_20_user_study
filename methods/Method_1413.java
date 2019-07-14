private Task<EncodedImage> foundPinnedImage(CacheKey key,EncodedImage pinnedImage){
  FLog.v(TAG,"Found image for %s in staging area",key.getUriString());
  mImageCacheStatsTracker.onStagingAreaHit(key);
  return Task.forResult(pinnedImage);
}
