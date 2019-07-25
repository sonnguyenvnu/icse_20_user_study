@Override public String[] directories(){
  String path=System.getenv("PATH");
  if (path != null) {
    return path.split(File.pathSeparator);
  }
 else {
    return new String[0];
  }
}
