/** 
 * Performs key-value loop up in staging area and file cache. Any error manifests itself as a miss, i.e. returns false.
 * @param key
 * @return true if the image is found in staging area or File cache, false if not found
 */
private boolean checkInStagingAreaAndFileCache(final CacheKey key){
  EncodedImage result=mStagingArea.get(key);
  if (result != null) {
    result.close();
    FLog.v(TAG,"Found image for %s in staging area",key.getUriString());
    mImageCacheStatsTracker.onStagingAreaHit(key);
    return true;
  }
 else {
    FLog.v(TAG,"Did not find image for %s in staging area",key.getUriString());
    mImageCacheStatsTracker.onStagingAreaMiss();
    try {
      return mFileCache.hasKey(key);
    }
 catch (    Exception exception) {
      return false;
    }
  }
}
