/** 
 * Checks the cache constraints to determine whether the new value can be cached or not. 
 */
private synchronized boolean canCacheNewValue(V value){
  int newValueSize=mValueDescriptor.getSizeInBytes(value);
  return (newValueSize <= mMemoryCacheParams.maxCacheEntrySize) && (getInUseCount() <= mMemoryCacheParams.maxCacheEntries - 1) && (getInUseSizeInBytes() <= mMemoryCacheParams.maxCacheSize - newValueSize);
}
