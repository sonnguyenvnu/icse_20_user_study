/** 
 * Returns whether the underlying libraries are available, loading them if necessary.
 */
public synchronized boolean isAvailable(){
  if (loadAttempted) {
    return isAvailable;
  }
  loadAttempted=true;
  try {
    for (    String lib : nativeLibraries) {
      System.loadLibrary(lib);
    }
    isAvailable=true;
  }
 catch (  UnsatisfiedLinkError exception) {
  }
  return isAvailable;
}
