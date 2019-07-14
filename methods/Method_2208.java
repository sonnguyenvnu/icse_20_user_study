/** 
 * Iterates through all entries cached in counting cache and builds snapshot of its content. This should be used by tools that need to know current content of given cache. <p> Caller should call release method on returned DumpInfo after it is done with examining cache contents
 */
public DumpInfo dumpCacheContent(){
synchronized (mCountingBitmapCache) {
    DumpInfo<K,V> dumpInfo=new DumpInfo<>(mCountingBitmapCache.getSizeInBytes(),mCountingBitmapCache.getEvictionQueueSizeInBytes(),mCountingBitmapCache.mMemoryCacheParams);
    final List<LinkedHashMap.Entry<K,CountingMemoryCache.Entry<K,V>>> cachedEntries=mCountingBitmapCache.mCachedEntries.getMatchingEntries(null);
    for (    LinkedHashMap.Entry<K,CountingMemoryCache.Entry<K,V>> cachedEntry : cachedEntries) {
      CountingMemoryCache.Entry<K,V> entry=cachedEntry.getValue();
      DumpInfoEntry<K,V> dumpEntry=new DumpInfoEntry<>(entry.key,entry.valueRef);
      if (entry.clientCount > 0) {
        dumpInfo.sharedEntries.add(dumpEntry);
      }
 else {
        dumpInfo.lruEntries.add(dumpEntry);
      }
    }
    for (    Map.Entry<Bitmap,Object> entry : mCountingBitmapCache.mOtherEntries.entrySet()) {
      if (entry != null && !entry.getKey().isRecycled()) {
        dumpInfo.otherEntries.put(entry.getKey(),entry.getValue());
      }
    }
    return dumpInfo;
  }
}
