/** 
 * Updates the cache params (constraints) if enough time has passed since the last update.
 */
private synchronized void maybeUpdateCacheParams(){
  if (mLastCacheParamsCheck + mMemoryCacheParams.paramsCheckIntervalMs > SystemClock.uptimeMillis()) {
    return;
  }
  mLastCacheParamsCheck=SystemClock.uptimeMillis();
  mMemoryCacheParams=mMemoryCacheParamsSupplier.get();
}
