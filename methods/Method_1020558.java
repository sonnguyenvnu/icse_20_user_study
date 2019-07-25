private void arg(String name,EGLContext object){
  if (object == EGL10.EGL_NO_CONTEXT) {
    arg(name,"EGL10.EGL_NO_CONTEXT");
  }
 else {
    arg(name,toString(object));
  }
}
