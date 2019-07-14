@Override public void drawBuffer(int buf){
  if (gl2x != null) {
    gl2x.glDrawBuffer(buf);
  }
 else   if (gl3 != null) {
    gl3.glDrawBuffer(buf);
  }
 else   if (gl3es3 != null) {
    IntBuffer intBuffer=IntBuffer.allocate(1);
    intBuffer.put(buf);
    intBuffer.rewind();
    gl3es3.glDrawBuffers(1,intBuffer);
  }
 else {
    throw new RuntimeException(String.format(MISSING_GLFUNC_ERROR,"glDrawBuffer()"));
  }
}
