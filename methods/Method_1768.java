/** 
 * Calculates an eviction score. Entries with a higher eviction score should be evicted first.
 */
@VisibleForTesting float calculateScore(DiskStorage.Entry entry,long now){
  long ageMs=now - entry.getTimestamp();
  long bytes=entry.getSize();
  return mAgeWeight * ageMs + mSizeWeight * bytes;
}
