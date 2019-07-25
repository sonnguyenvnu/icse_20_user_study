@Override public int fill(Supplier<E> s,int limit){
  final int mask=this.mask;
  final long capacity=mask + 1;
  long producerLimit=lvProducerLimit();
  long pIndex;
  int actualLimit=0;
  do {
    pIndex=lvProducerIndex();
    long available=producerLimit - pIndex;
    if (available <= 0) {
      final long cIndex=lvConsumerIndex();
      producerLimit=cIndex + capacity;
      available=producerLimit - pIndex;
      if (available <= 0) {
        return 0;
      }
 else {
        soProducerLimit(producerLimit);
      }
    }
    actualLimit=Math.min((int)available,limit);
  }
 while (!casProducerIndex(pIndex,pIndex + actualLimit));
  final AtomicReferenceArray<E> buffer=this.buffer;
  for (int i=0; i < actualLimit; i++) {
    final int offset=calcElementOffset(pIndex + i,mask);
    soElement(buffer,offset,s.get());
  }
  return actualLimit;
}
