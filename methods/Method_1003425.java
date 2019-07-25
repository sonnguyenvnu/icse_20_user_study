/** 
 * Store a file (STOR).
 * @param fileName the file name
 * @param in the input stream
 */
public void store(String fileName,InputStream in) throws IOException {
  passive();
  send("STOR " + fileName);
  readCode(150);
  IOUtils.copyAndClose(in,outData);
  readCode(226);
}
