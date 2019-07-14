protected static ByteBuffer updateByteBuffer(ByteBuffer buf,byte[] arr,boolean wrap){
  if (USE_DIRECT_BUFFERS) {
    if (buf == null || buf.capacity() < arr.length) {
      buf=allocateDirectByteBuffer(arr.length);
    }
    buf.position(0);
    buf.put(arr);
    buf.rewind();
  }
 else {
    if (wrap) {
      buf=ByteBuffer.wrap(arr);
    }
 else {
      if (buf == null || buf.capacity() < arr.length) {
        buf=ByteBuffer.allocate(arr.length);
      }
      buf.position(0);
      buf.put(arr);
      buf.rewind();
    }
  }
  return buf;
}
