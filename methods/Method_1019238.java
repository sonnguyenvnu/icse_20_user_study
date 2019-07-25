/** 
 * Prepare this SoSource extracting a corrupted library. 
 */
protected synchronized void prepare(String soName) throws IOException {
  Object lock=getLibraryLock(soName);
synchronized (lock) {
    mCorruptedLib=soName;
    prepare(SoSource.PREPARE_FLAG_FORCE_REFRESH);
  }
}
