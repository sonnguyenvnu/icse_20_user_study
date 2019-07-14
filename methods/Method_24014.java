protected boolean hasAutoMipmapGenSupport(){
  int major=getGLVersion()[0];
  if (isES() && major >= 2) {
    return true;
  }
 else   if (!isES() && major >= 3) {
    return true;
  }
 else {
    String ext=getString(EXTENSIONS);
    return -1 < ext.indexOf("_generate_mipmap");
  }
}
