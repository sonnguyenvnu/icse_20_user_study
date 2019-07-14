protected static ShortBuffer updateShortBuffer(ShortBuffer buf,short[] arr,boolean wrap){
  if (USE_DIRECT_BUFFERS) {
    if (buf == null || buf.capacity() < arr.length) {
      buf=allocateDirectShortBuffer(arr.length);
    }
    buf.position(0);
    buf.put(arr);
    buf.rewind();
  }
 else {
    if (wrap) {
      buf=ShortBuffer.wrap(arr);
    }
 else {
      if (buf == null || buf.capacity() < arr.length) {
        buf=ShortBuffer.allocate(arr.length);
      }
      buf.position(0);
      buf.put(arr);
      buf.rewind();
    }
  }
  return buf;
}
