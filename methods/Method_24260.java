@Override public void readBuffer(int buf){
  if (gl2x != null) {
    gl2x.glReadBuffer(buf);
  }
 else   if (gl3 != null) {
    gl3.glReadBuffer(buf);
  }
 else   if (gl3es3 != null) {
    gl3es3.glReadBuffer(buf);
  }
 else {
    throw new RuntimeException(String.format(MISSING_GLFUNC_ERROR,"glReadBuffer()"));
  }
}
