protected static void putShortArray(ShortBuffer buf,short[] arr){
  if (!buf.hasArray() || buf.array() != arr) {
    buf.position(0);
    buf.put(arr);
    buf.rewind();
  }
}
