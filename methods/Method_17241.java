/** 
 * Determines the bucket that the timer event should be added to.
 * @param time the time when the event fires
 * @return the sentinel at the head of the bucket
 */
Node<K,V> findBucket(long time){
  long duration=time - nanos;
  int length=wheel.length - 1;
  for (int i=0; i < length; i++) {
    if (duration < SPANS[i + 1]) {
      int ticks=(int)(time >> SHIFT[i]);
      int index=ticks & (wheel[i].length - 1);
      return wheel[i][index];
    }
  }
  return wheel[length][0];
}
