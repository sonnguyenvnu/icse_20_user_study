protected static void getShortArray(ShortBuffer buf,short[] arr){
  if (!buf.hasArray() || buf.array() != arr) {
    buf.position(0);
    buf.get(arr);
    buf.rewind();
  }
}
