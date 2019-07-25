@Override public String[] directories(){
  String path=System.getProperty("user.dir");
  if (path != null) {
    return new String[]{path};
  }
 else {
    return new String[0];
  }
}
