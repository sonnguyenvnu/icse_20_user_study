@Override public int offer(E e){
  int mask;
  int result=0;
  Buffer<E> buffer;
  boolean uncontended=true;
  Buffer<E>[] buffers=table;
  if ((buffers == null) || (mask=buffers.length - 1) < 0 || (buffer=buffers[getProbe() & mask]) == null || !(uncontended=((result=buffer.offer(e)) != Buffer.FAILED))) {
    expandOrRetry(e,uncontended);
  }
  return result;
}
