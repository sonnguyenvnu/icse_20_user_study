@Override public int drain(final Consumer<E> c,final int limit){
  final AtomicReferenceArray<E> buffer=this.buffer;
  final int mask=this.mask;
  long currProducerIndexCache=lvProducerIndexCache();
  int adjustedLimit=0;
  long currentConsumerIndex;
  do {
    currentConsumerIndex=lvConsumerIndex();
    if (currentConsumerIndex >= currProducerIndexCache) {
      long currProducerIndex=lvProducerIndex();
      if (currentConsumerIndex >= currProducerIndex) {
        return 0;
      }
 else {
        currProducerIndexCache=currProducerIndex;
        svProducerIndexCache(currProducerIndex);
      }
    }
    int remaining=(int)(currProducerIndexCache - currentConsumerIndex);
    adjustedLimit=Math.min(remaining,limit);
  }
 while (!casConsumerIndex(currentConsumerIndex,currentConsumerIndex + adjustedLimit));
  for (int i=0; i < adjustedLimit; i++) {
    c.accept(removeElement(buffer,currentConsumerIndex + i,mask));
  }
  return adjustedLimit;
}
