protected void relocation(Data<V> newValue,long newEntrySize){
  long oldHashLookupPos=hlp.hashLookupPos;
  long oldHashLookupAddr=s.tierBaseAddr;
  boolean tierHasChanged=allocatedChunks.initEntryAndKeyCopying(newEntrySize,valueSizeOffset - keySizeOffset,pos,entrySizeInChunks);
  if (tierHasChanged) {
    if (!ks.searchStateAbsent())     throw new AssertionError();
  }
  initValue(newValue);
  freeExtraAllocatedChunks();
  CompactOffHeapLinearHashTable hl=hh.h().hashLookup;
  long hashLookupKey=hl.key(hl.readEntry(oldHashLookupAddr,oldHashLookupPos));
  hl.checkValueForPut(pos);
  hl.writeEntryVolatile(s.tierBaseAddr,hlp.hashLookupPos,hashLookupKey,pos);
  s.innerWriteLock.lock();
  if (tierHasChanged)   hl.remove(oldHashLookupAddr,oldHashLookupPos);
}
