/** 
 * Return an InputStream for a file inside the Processing lib folder.
 */
static public InputStream getLibStream(String filename) throws IOException {
  return new FileInputStream(getLibFile(filename));
}
