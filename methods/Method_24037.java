protected static IntBuffer updateIntBuffer(IntBuffer buf,int[] arr,boolean wrap){
  if (USE_DIRECT_BUFFERS) {
    if (buf == null || buf.capacity() < arr.length) {
      buf=allocateDirectIntBuffer(arr.length);
    }
    buf.position(0);
    buf.put(arr);
    buf.rewind();
  }
 else {
    if (wrap) {
      buf=IntBuffer.wrap(arr);
    }
 else {
      if (buf == null || buf.capacity() < arr.length) {
        buf=IntBuffer.allocate(arr.length);
      }
      buf.position(0);
      buf.put(arr);
      buf.rewind();
    }
  }
  return buf;
}
