/** 
 * Locks a file.
 * @param file the file to lock
 */
private static FileChannel lock(Path file){
  try {
    FileChannel fc=FileChannel.open(file);
    if (fc.tryLock(0L,Long.MAX_VALUE,true) == null) {
      if (Configuration.DEBUG_LOADER.get(false)) {
        apiLog("\tFile is locked by another process, waiting...");
      }
      fc.lock(0L,Long.MAX_VALUE,true);
    }
    return fc;
  }
 catch (  Exception e) {
    throw new RuntimeException("Failed to lock file.",e);
  }
}
