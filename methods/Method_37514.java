/** 
 * Checks if  {@link File} is a file. Throws IOException if not.
 * @param file {@link File}
 * @throws IOException if {@link File} is not a file.
 */
private static void checkIsFile(final File file) throws IOException {
  if (!file.isFile()) {
    throw new IOException(MSG_NOT_A_FILE + file);
  }
}
