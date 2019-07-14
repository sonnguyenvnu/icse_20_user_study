private E newBufferPoll(E[] nextBuffer,final long index){
  final long offsetInNew=newBufferAndOffset(nextBuffer,index);
  final E n=lvElement(nextBuffer,offsetInNew);
  if (n == null) {
    throw new IllegalStateException("new buffer must have at least one element");
  }
  soElement(nextBuffer,offsetInNew,null);
  soConsumerIndex(index + 2);
  return n;
}
