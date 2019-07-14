protected static void getFloatArray(FloatBuffer buf,float[] arr){
  if (!buf.hasArray() || buf.array() != arr) {
    buf.position(0);
    buf.get(arr);
    buf.rewind();
  }
}
