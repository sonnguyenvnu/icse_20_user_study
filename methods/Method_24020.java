protected boolean hasReadBuffer(){
  int[] version=getGLVersion();
  if (isES()) {
    return version[0] >= 3;
  }
  return version[0] >= 2;
}
