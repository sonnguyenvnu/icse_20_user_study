private void checkEglError(String msg){
  if (mEGL.eglGetError() != EGL10.EGL_SUCCESS) {
    throw new RuntimeException("EGL error encountered (see log)");
  }
}
