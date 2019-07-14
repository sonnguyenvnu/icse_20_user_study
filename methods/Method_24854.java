private static List<String> buildSearchLibraryClassPath(JavaMode mode){
  StringBuilder classPath=new StringBuilder();
  for (  Library lib : mode.contribLibraries) {
    classPath.append(File.pathSeparator).append(lib.getClassPath());
  }
  return sanitizeClassPath(classPath.toString());
}
