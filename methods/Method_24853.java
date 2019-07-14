private static List<String> buildCoreLibraryClassPath(JavaMode mode){
  StringBuilder classPath=new StringBuilder();
  for (  Library lib : mode.coreLibraries) {
    classPath.append(File.pathSeparator).append(lib.getClassPath());
  }
  return sanitizeClassPath(classPath.toString());
}
