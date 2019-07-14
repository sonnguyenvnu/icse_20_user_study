/** 
 * Implements the Unix "touch" utility. It creates a new  {@link File}with size 0 or, if the  {@link File} exists already, it is opened andclosed without modifying it, but updating the  {@link File} date and time.
 */
public static void touch(final File file) throws IOException {
  if (!file.exists()) {
    StreamUtil.close(new FileOutputStream(file,false));
  }
  file.setLastModified(System.currentTimeMillis());
}
