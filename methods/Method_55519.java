/** 
 * Loads a JNI shared library.
 * @param load        should be the {@code System::load} expression. This ensures that {@code System.load} has the same caller as this method.
 * @param loadLibrary should be the {@code System::loadLibrary} expression. This ensures that {@code System.loadLibrary} has the same caller as thismethod.
 * @param context     the class to use to discover the shared library in the classpath
 * @param name        the library name. If not an absolute path, it must be the plain library name, without an OS specific prefix or file extension (e.g.GL, not libGL.so)
 * @throws UnsatisfiedLinkError if the library could not be loaded
 */
@SuppressWarnings("try") public static void loadSystem(Consumer<String> load,Consumer<String> loadLibrary,Class<?> context,String name) throws UnsatisfiedLinkError {
  apiLog("Loading library (system): " + name);
  if (Paths.get(name).isAbsolute()) {
    load.accept(name);
    apiLog("\tSuccess");
    return;
  }
  String libName=Platform.get().mapLibraryName(name);
  URL libURL=findURL(context,libName,name.contains("lwjgl"));
  if (libURL == null) {
    if (loadSystemFromLibraryPath(load,context,libName)) {
      return;
    }
  }
 else {
    boolean debugLoader=Configuration.DEBUG_LOADER.get(false);
    try {
      if (debugLoader) {
        apiLog("\tUsing SharedLibraryLoader...");
      }
      try (FileChannel ignored=SharedLibraryLoader.load(name,libName,libURL)){
        if (loadSystemFromLibraryPath(load,context,libName)) {
          return;
        }
      }
     }
 catch (    Exception e) {
      if (debugLoader) {
        e.printStackTrace(DEBUG_STREAM);
      }
    }
  }
  try {
    loadLibrary.accept(name);
    String paths=System.getProperty(JAVA_LIBRARY_PATH);
    Path libFile=paths == null ? null : findFile(paths,libName);
    if (libFile != null) {
      apiLog(String.format("\tLoaded from %s: %s",JAVA_LIBRARY_PATH,libFile));
      checkHash(context,libFile);
    }
 else {
      apiLog("\tLoaded from a ClassLoader provided path.");
    }
    return;
  }
 catch (  Throwable t) {
    apiLog(String.format("\t%s not found in %s",libName,JAVA_LIBRARY_PATH));
  }
  printError(true);
  throw new UnsatisfiedLinkError("Failed to locate library: " + libName);
}
