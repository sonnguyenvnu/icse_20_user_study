protected static FloatBuffer allocateFloatBuffer(float[] arr){
  if (USE_DIRECT_BUFFERS) {
    FloatBuffer buf=allocateDirectFloatBuffer(arr.length);
    buf.put(arr);
    buf.position(0);
    return buf;
  }
 else {
    return FloatBuffer.wrap(arr);
  }
}
