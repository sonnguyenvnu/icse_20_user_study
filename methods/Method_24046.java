protected static void putFloatArray(FloatBuffer buf,float[] arr){
  if (!buf.hasArray() || buf.array() != arr) {
    buf.position(0);
    buf.put(arr);
    buf.rewind();
  }
}
