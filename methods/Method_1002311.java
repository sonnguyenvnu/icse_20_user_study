@Override public int fill(final Supplier<E> s,final int limit){
  final AtomicReferenceArray<E> buffer=this.buffer;
  final int mask=this.mask;
  long producerIndex=this.lpProducerIndex();
  for (int i=0; i < limit; i++) {
    final int offset=calcElementOffset(producerIndex,mask);
    if (null != lvElement(buffer,offset)) {
      return i;
    }
    producerIndex++;
    soElement(buffer,offset,s.get());
    soProducerIndex(producerIndex);
  }
  return limit;
}
