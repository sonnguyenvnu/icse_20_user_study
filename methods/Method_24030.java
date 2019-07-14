protected static ShortBuffer allocateShortBuffer(short[] arr){
  if (USE_DIRECT_BUFFERS) {
    ShortBuffer buf=allocateDirectShortBuffer(arr.length);
    buf.put(arr);
    buf.position(0);
    return buf;
  }
 else {
    return ShortBuffer.wrap(arr);
  }
}
