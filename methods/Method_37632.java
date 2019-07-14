/** 
 * Compresses a file into zlib archive.
 */
public static File zlib(final String file) throws IOException {
  return zlib(new File(file));
}
