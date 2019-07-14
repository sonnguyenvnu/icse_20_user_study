/** 
 * Returns the input stream of the trace data. 
 */
protected InputStream readFile() throws IOException {
  BufferedInputStream input=new BufferedInputStream(openFile(),BUFFER_SIZE);
  input.mark(100);
  try {
    return new XZInputStream(input);
  }
 catch (  IOException e) {
    input.reset();
  }
  try {
    return new CompressorStreamFactory().createCompressorInputStream(input);
  }
 catch (  CompressorException e) {
    input.reset();
  }
  try {
    return new ArchiveStreamFactory().createArchiveInputStream(input);
  }
 catch (  ArchiveException e) {
    input.reset();
  }
  return input;
}
