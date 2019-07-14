private void moveToFront(LinkedEntry<T> bucket){
  if (mHead == bucket) {
    return;
  }
  prune(bucket);
  if (mHead == null) {
    mHead=bucket;
    mTail=bucket;
    return;
  }
  bucket.next=mHead;
  mHead.prev=bucket;
  mHead=bucket;
}
