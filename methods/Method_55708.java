private static void initExtractPath(Path extractPath){
  String newLibPath=extractPath.toAbsolutePath().toString();
  String libPath=Configuration.LIBRARY_PATH.get();
  if (libPath != null && !libPath.isEmpty()) {
    newLibPath+=File.pathSeparator + libPath;
  }
  System.setProperty(Configuration.LIBRARY_PATH.getProperty(),newLibPath);
  Configuration.LIBRARY_PATH.set(newLibPath);
}
