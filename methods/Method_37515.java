/** 
 * Checks if  {@link File} is a directory. Throws IOException if not.
 * @param dir Directory
 * @throws IOException if {@link File} is not a directory.
 */
private static void checkIsDirectory(final File dir) throws IOException {
  if (!dir.isDirectory()) {
    throw new IOException(MSG_NOT_A_DIRECTORY + dir);
  }
}
