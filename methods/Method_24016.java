protected boolean hasPackedDepthStencilSupport(){
  int major=getGLVersion()[0];
  if (major < 3) {
    String ext=getString(EXTENSIONS);
    return -1 < ext.indexOf("_packed_depth_stencil");
  }
 else {
    return true;
  }
}
