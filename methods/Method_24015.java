protected boolean hasFboMultisampleSupport(){
  int major=getGLVersion()[0];
  if (major < 3) {
    String ext=getString(EXTENSIONS);
    return -1 < ext.indexOf("_framebuffer_multisample");
  }
 else {
    return true;
  }
}
