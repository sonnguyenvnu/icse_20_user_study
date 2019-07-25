public long size(){
  goToFirstTier();
  long size=tierEntries() - tierDeleted();
  while (hasNextTier()) {
    nextTier();
    size+=tierEntries() - tierDeleted();
  }
  return size;
}
