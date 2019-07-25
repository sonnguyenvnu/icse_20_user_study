/** 
 * Get the size of a file (SIZE).
 * @param fileName the file name
 * @return the size
 */
long size(String fileName) throws IOException {
  send("SIZE " + fileName);
  readCode(250);
  long size=Long.parseLong(message);
  return size;
}
