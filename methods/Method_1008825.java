/** 
 * Finds and removes  {@code entry} from the bucket linked lists in both the key-to-value directionand the value-to-key direction.
 */
private void delete(BiEntry<K,V> entry){
  int keyBucket=entry.keyHash & mask;
  BiEntry<K,V> prevBucketEntry=null;
  for (BiEntry<K,V> bucketEntry=hashTableKToV[keyBucket]; true; bucketEntry=bucketEntry.nextInKToVBucket) {
    if (bucketEntry == entry) {
      if (prevBucketEntry == null) {
        hashTableKToV[keyBucket]=entry.nextInKToVBucket;
      }
 else {
        prevBucketEntry.nextInKToVBucket=entry.nextInKToVBucket;
      }
      break;
    }
    prevBucketEntry=bucketEntry;
  }
  int valueBucket=entry.valueHash & mask;
  prevBucketEntry=null;
  for (BiEntry<K,V> bucketEntry=hashTableVToK[valueBucket]; true; bucketEntry=bucketEntry.nextInVToKBucket) {
    if (bucketEntry == entry) {
      if (prevBucketEntry == null) {
        hashTableVToK[valueBucket]=entry.nextInVToKBucket;
      }
 else {
        prevBucketEntry.nextInVToKBucket=entry.nextInVToKBucket;
      }
      break;
    }
    prevBucketEntry=bucketEntry;
  }
  if (entry.prevInKeyInsertionOrder == null) {
    firstInKeyInsertionOrder=entry.nextInKeyInsertionOrder;
  }
 else {
    entry.prevInKeyInsertionOrder.nextInKeyInsertionOrder=entry.nextInKeyInsertionOrder;
  }
  if (entry.nextInKeyInsertionOrder == null) {
    lastInKeyInsertionOrder=entry.prevInKeyInsertionOrder;
  }
 else {
    entry.nextInKeyInsertionOrder.prevInKeyInsertionOrder=entry.prevInKeyInsertionOrder;
  }
  size--;
  modCount++;
}
