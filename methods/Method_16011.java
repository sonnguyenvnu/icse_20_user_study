public static String getClassPath(String path,String packages){
  if (path.endsWith(".jar")) {
    return path;
  }
  boolean isJar=path.contains("!/") && path.contains(".jar");
  if (isJar) {
    return path.substring(0,path.lastIndexOf(".jar") + 4);
  }
  int pos=path.endsWith("/") ? 2 : 1;
  return path.substring(0,path.length() - packages.length() - pos);
}
