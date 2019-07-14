protected static ByteBuffer allocateByteBuffer(byte[] arr){
  if (USE_DIRECT_BUFFERS) {
    ByteBuffer buf=allocateDirectByteBuffer(arr.length);
    buf.put(arr);
    buf.position(0);
    return buf;
  }
 else {
    return ByteBuffer.wrap(arr);
  }
}
