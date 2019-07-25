@Override public String[] directories(){
  return System.getProperty("jna.library.path").split(File.pathSeparator);
}
