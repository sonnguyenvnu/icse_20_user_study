protected boolean hasAnisoSamplingSupport(){
  int major=getGLVersion()[0];
  if (isES() || major < 3) {
    String ext=getString(EXTENSIONS);
    return -1 < ext.indexOf("_texture_filter_anisotropic");
  }
 else {
    return true;
  }
}
