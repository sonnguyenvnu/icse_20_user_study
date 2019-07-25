/** 
 * Initializes class and reads stream. Init does not close stream.
 * @param in InputStream to read from new array with size + inc
 * @throws IOException In case of an I/O problem
 */
private void init(InputStream in) throws java.io.IOException {
  this.file=IOUtils.toByteArray(in);
  this.fsize=this.file.length;
  this.current=0;
}
