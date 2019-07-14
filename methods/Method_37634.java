/** 
 * Decompress gzip archive.
 */
public static File ungzip(final String file) throws IOException {
  return ungzip(new File(file));
}
