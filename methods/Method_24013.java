protected boolean hasNpotTexSupport(){
  int major=getGLVersion()[0];
  if (major < 3) {
    String ext=getString(EXTENSIONS);
    if (isES()) {
      return -1 < ext.indexOf("_texture_npot");
    }
 else {
      return -1 < ext.indexOf("_texture_non_power_of_two");
    }
  }
 else {
    return true;
  }
}
