@Override protected int getGLSLVersion(){
  VersionNumber vn=context.getGLSLVersionNumber();
  return vn.getMajor() * 100 + vn.getMinor();
}
