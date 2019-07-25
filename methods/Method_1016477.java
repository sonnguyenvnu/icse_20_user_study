/** 
 * @return records in file
 * @throws IOException
 */
private final long filesize() throws IOException {
  return this.raf.length() / this.recordsize;
}
