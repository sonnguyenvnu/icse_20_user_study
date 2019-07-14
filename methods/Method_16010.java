public static String getClassPath(Class type){
  ProtectionDomain domain=type.getProtectionDomain();
  CodeSource codeSource=domain.getCodeSource();
  if (codeSource == null) {
    return getClassPath(type.getResource("").getPath(),type.getPackage().getName());
  }
  String path=codeSource.getLocation().toString();
  boolean isJar=path.contains("!/") && path.contains(".jar");
  if (isJar) {
    return path.substring(0,path.lastIndexOf(".jar") + 4);
  }
  if (path.endsWith("/")) {
    return path.substring(0,path.length() - 1);
  }
  return path;
}
