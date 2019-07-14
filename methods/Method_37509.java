/** 
 * Checks the start of the file for ASCII control characters
 * @param file {@link File}
 * @return true if the the start of the {@link File} is ASCII control characters.
 */
public static boolean isBinary(final File file) throws IOException {
  byte[] bytes=readBytes(file,128);
  for (  byte b : bytes) {
    if (b < 32 && b != 9 && b != 10 && b != 13) {
      return true;
    }
  }
  return false;
}
