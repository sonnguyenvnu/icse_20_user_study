/** 
 * @see Sequencer#next(int)
 */
@Override public long next(int n){
  if (n < 1) {
    throw new IllegalArgumentException("n must be > 0");
  }
  long current;
  long next;
  do {
    current=cursor.get();
    next=current + n;
    long wrapPoint=next - bufferSize;
    long cachedGatingSequence=gatingSequenceCache.get();
    if (wrapPoint > cachedGatingSequence || cachedGatingSequence > current) {
      long gatingSequence=Util.getMinimumSequence(gatingSequences,current);
      if (wrapPoint > gatingSequence) {
        waitStrategy.signalAllWhenBlocking();
        LockSupport.parkNanos(1);
        continue;
      }
      gatingSequenceCache.set(gatingSequence);
    }
 else     if (cursor.compareAndSet(current,next)) {
      break;
    }
  }
 while (true);
  return next;
}
