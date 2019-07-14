@SuppressWarnings("unchecked") private E[] getNextBuffer(final E[] buffer,final long mask){
  final long nextArrayOffset=nextArrayOffset(mask);
  final E[] nextBuffer=(E[])lvElement(buffer,nextArrayOffset);
  soElement(buffer,nextArrayOffset,null);
  return nextBuffer;
}
