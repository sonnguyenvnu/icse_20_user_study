/** 
 * Reads all data from given input stream.
 * @param stream Stream to read data from
 * @throws IOException in case of problems while reading from the stream
 */
public void load(final InputStream stream) throws IOException {
  final ExecutionDataReader reader=new ExecutionDataReader(new BufferedInputStream(stream));
  reader.setExecutionDataVisitor(executionData);
  reader.setSessionInfoVisitor(sessionInfos);
  reader.read();
}
