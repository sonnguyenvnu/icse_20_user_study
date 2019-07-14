private synchronized void prune(LinkedEntry<T> bucket){
  LinkedEntry<T> prev=bucket.prev;
  LinkedEntry<T> next=bucket.next;
  if (prev != null) {
    prev.next=next;
  }
  if (next != null) {
    next.prev=prev;
  }
  bucket.prev=null;
  bucket.next=null;
  if (bucket == mHead) {
    mHead=next;
  }
  if (bucket == mTail) {
    mTail=prev;
  }
}
