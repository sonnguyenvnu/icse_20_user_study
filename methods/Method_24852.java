private static List<String> buildModeClassPath(JavaMode mode,boolean search){
  StringBuilder classPath=new StringBuilder();
  if (search) {
    String searchClassPath=mode.getSearchPath();
    if (searchClassPath != null) {
      classPath.append(File.pathSeparator).append(searchClassPath);
    }
  }
 else {
    Library coreLibrary=mode.getCoreLibrary();
    String coreClassPath=coreLibrary != null ? coreLibrary.getClassPath() : mode.getSearchPath();
    if (coreClassPath != null) {
      classPath.append(File.pathSeparator).append(coreClassPath);
    }
  }
  return sanitizeClassPath(classPath.toString());
}
