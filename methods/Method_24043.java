protected static FloatBuffer updateFloatBuffer(FloatBuffer buf,float[] arr,boolean wrap){
  if (USE_DIRECT_BUFFERS) {
    if (buf == null || buf.capacity() < arr.length) {
      buf=allocateDirectFloatBuffer(arr.length);
    }
    buf.position(0);
    buf.put(arr);
    buf.rewind();
  }
 else {
    if (wrap) {
      buf=FloatBuffer.wrap(arr);
    }
 else {
      if (buf == null || buf.capacity() < arr.length) {
        buf=FloatBuffer.allocate(arr.length);
      }
      buf.position(0);
      buf.put(arr);
      buf.rewind();
    }
  }
  return buf;
}
