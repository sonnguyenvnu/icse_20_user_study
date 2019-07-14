protected static void putIntArray(IntBuffer buf,int[] arr){
  if (!buf.hasArray() || buf.array() != arr) {
    buf.position(0);
    buf.put(arr);
    buf.rewind();
  }
}
