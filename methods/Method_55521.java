@SuppressWarnings("try") private static SharedLibrary loadNative(Class<?> context,String name,boolean bundledWithLWJGL,boolean printError){
  apiLog("Loading library: " + name);
  if (Paths.get(name).isAbsolute()) {
    SharedLibrary lib=apiCreateLibrary(name);
    apiLog("\tSuccess");
    return lib;
  }
  String libName=Platform.get().mapLibraryName(name);
  SharedLibrary lib;
  URL libURL=findURL(context,libName,bundledWithLWJGL);
  if (libURL == null) {
    lib=loadNativeFromLibraryPath(context,libName);
    if (lib != null) {
      return lib;
    }
  }
 else {
    boolean debugLoader=Configuration.DEBUG_LOADER.get(false);
    try {
      if (debugLoader) {
        apiLog("\tUsing SharedLibraryLoader...");
      }
      try (FileChannel ignored=SharedLibraryLoader.load(name,libName,libURL)){
        lib=loadNativeFromLibraryPath(context,libName);
        if (lib != null) {
          return lib;
        }
      }
     }
 catch (    Exception e) {
      if (debugLoader) {
        e.printStackTrace(DEBUG_STREAM);
      }
    }
  }
  if (!bundledWithLWJGL) {
    lib=loadNativeFromSystem(libName);
    if (lib != null) {
      return lib;
    }
  }
{
    if (Configuration.EMULATE_SYSTEM_LOADLIBRARY.get(false)) {
      try {
        Method findLibrary=ClassLoader.class.getDeclaredMethod("findLibrary",String.class);
        findLibrary.setAccessible(true);
        String libPath=(String)findLibrary.invoke(context.getClassLoader(),name);
        if (libPath != null) {
          lib=apiCreateLibrary(libPath);
          apiLog(String.format("\tLoaded from ClassLoader provided path: %s",libPath));
          return lib;
        }
      }
 catch (      Exception ignored) {
      }
    }
    String paths=System.getProperty(JAVA_LIBRARY_PATH);
    if (paths != null) {
      lib=loadNative(context,libName,JAVA_LIBRARY_PATH,paths);
      if (lib != null) {
        return lib;
      }
    }
  }
  if (bundledWithLWJGL) {
    lib=loadNativeFromSystem(libName);
    if (lib != null) {
      return lib;
    }
  }
  if (printError) {
    printError(bundledWithLWJGL);
  }
  throw new UnsatisfiedLinkError("Failed to locate library: " + libName);
}
