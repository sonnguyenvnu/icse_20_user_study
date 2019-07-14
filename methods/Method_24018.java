protected boolean hasSynchronization(){
  int[] version=getGLVersion();
  if (isES()) {
    return version[0] >= 3;
  }
  return (version[0] > 3) || (version[0] == 3 && version[1] >= 2);
}
