public static MacOSXLibrary getWithIdentifier(String bundleID){
  apiLog("Loading library: " + bundleID);
  MacOSXLibraryBundle lib=MacOSXLibraryBundle.getWithIdentifier(bundleID);
  apiLog("\tSuccess");
  return lib;
}
