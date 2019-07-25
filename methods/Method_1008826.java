private void insert(BiEntry<K,V> entry,@Nullable BiEntry<K,V> oldEntryForKey){
  int keyBucket=entry.keyHash & mask;
  entry.nextInKToVBucket=hashTableKToV[keyBucket];
  hashTableKToV[keyBucket]=entry;
  int valueBucket=entry.valueHash & mask;
  entry.nextInVToKBucket=hashTableVToK[valueBucket];
  hashTableVToK[valueBucket]=entry;
  if (oldEntryForKey == null) {
    entry.prevInKeyInsertionOrder=lastInKeyInsertionOrder;
    entry.nextInKeyInsertionOrder=null;
    if (lastInKeyInsertionOrder == null) {
      firstInKeyInsertionOrder=entry;
    }
 else {
      lastInKeyInsertionOrder.nextInKeyInsertionOrder=entry;
    }
    lastInKeyInsertionOrder=entry;
  }
 else {
    entry.prevInKeyInsertionOrder=oldEntryForKey.prevInKeyInsertionOrder;
    if (entry.prevInKeyInsertionOrder == null) {
      firstInKeyInsertionOrder=entry;
    }
 else {
      entry.prevInKeyInsertionOrder.nextInKeyInsertionOrder=entry;
    }
    entry.nextInKeyInsertionOrder=oldEntryForKey.nextInKeyInsertionOrder;
    if (entry.nextInKeyInsertionOrder == null) {
      lastInKeyInsertionOrder=entry;
    }
 else {
      entry.nextInKeyInsertionOrder.prevInKeyInsertionOrder=entry;
    }
  }
  size++;
  modCount++;
}
