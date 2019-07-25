/** 
 * @return string
 * @throws IOException if fails to read.
 */
protected String read() throws IOException {
  BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
  return reader.readLine();
}
