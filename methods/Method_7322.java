private void checkEglError(String msg){
  boolean failed=false;
  while (EGL14.eglGetError() != EGL14.EGL_SUCCESS) {
    failed=true;
  }
  if (failed) {
    throw new RuntimeException("EGL error encountered (see log)");
  }
}
