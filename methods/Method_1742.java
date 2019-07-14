/** 
 * Creates the directory (and its parents, if necessary). In case of an exception, log an error message with the relevant parameters
 * @param directory the directory to create
 * @param message message to use
 * @throws IOException
 */
private void mkdirs(File directory,String message) throws IOException {
  try {
    FileUtils.mkdirs(directory);
  }
 catch (  FileUtils.CreateDirectoryException cde) {
    mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR,TAG,message,cde);
    throw cde;
  }
}
