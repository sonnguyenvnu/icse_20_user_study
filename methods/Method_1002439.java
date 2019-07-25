/** 
 * Get the resulting JSON output.
 * @return the resulting JSON output.
 * @throws IOException if there is an error generating the output.
 */
public String result() throws IOException {
  _jsonGenerator.flush();
  return _writer.toString();
}
