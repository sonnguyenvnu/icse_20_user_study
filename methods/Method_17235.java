@Override public int reads(){
  Buffer<E>[] buffers=table;
  if (buffers == null) {
    return 0;
  }
  int reads=0;
  for (  Buffer<E> buffer : buffers) {
    if (buffer != null) {
      reads+=buffer.reads();
    }
  }
  return reads;
}
