@Override public ByteBuf _XXXXX_(long ledgerId,long entryId) throws IOException {
  long startTime=MathUtils.nowInNano();
  if (log.isDebugEnabled()) {
    log.debug("Get Entry: {}@{}",ledgerId,entryId);
  }
  if (entryId == BookieProtocol.LAST_ADD_CONFIRMED) {
    return getLastEntry(ledgerId);
  }
  long stamp=writeCacheRotationLock.tryOptimisticRead();
  WriteCache localWriteCache=writeCache;
  WriteCache localWriteCacheBeingFlushed=writeCacheBeingFlushed;
  if (!writeCacheRotationLock.validate(stamp)) {
    stamp=writeCacheRotationLock.readLock();
    try {
      localWriteCache=writeCache;
      localWriteCacheBeingFlushed=writeCacheBeingFlushed;
    }
  finally {
      writeCacheRotationLock.unlockRead(stamp);
    }
  }
  ByteBuf entry=localWriteCache.get(ledgerId,entryId);
  if (entry != null) {
    recordSuccessfulEvent(dbLedgerStorageStats.getReadCacheHitStats(),startTime);
    recordSuccessfulEvent(dbLedgerStorageStats.getReadEntryStats(),startTime);
    return entry;
  }
  entry=localWriteCacheBeingFlushed.get(ledgerId,entryId);
  if (entry != null) {
    recordSuccessfulEvent(dbLedgerStorageStats.getReadCacheHitStats(),startTime);
    recordSuccessfulEvent(dbLedgerStorageStats.getReadEntryStats(),startTime);
    return entry;
  }
  entry=readCache.get(ledgerId,entryId);
  if (entry != null) {
    recordSuccessfulEvent(dbLedgerStorageStats.getReadCacheHitStats(),startTime);
    recordSuccessfulEvent(dbLedgerStorageStats.getReadEntryStats(),startTime);
    return entry;
  }
  long entryLocation;
  try {
    entryLocation=entryLocationIndex.getLocation(ledgerId,entryId);
    if (entryLocation == 0) {
      throw new NoEntryException(ledgerId,entryId);
    }
    entry=entryLogger.readEntry(ledgerId,entryId,entryLocation);
  }
 catch (  NoEntryException e) {
    recordFailedEvent(dbLedgerStorageStats.getReadEntryStats(),startTime);
    throw e;
  }
  readCache.put(ledgerId,entryId,entry);
  long nextEntryLocation=entryLocation + 4 + entry.readableBytes();
  fillReadAheadCache(ledgerId,entryId + 1,nextEntryLocation);
  recordSuccessfulEvent(dbLedgerStorageStats.getReadCacheMissStats(),startTime);
  recordSuccessfulEvent(dbLedgerStorageStats.getReadEntryStats(),startTime);
  return entry;
}