/** 
 * Creates a instrumented version of the given class if possible. The provided  {@link InputStream} is not closed by this method.
 * @param input stream to read class definition from
 * @param name a name used for exception messages
 * @return instrumented definition
 * @throws IOException if reading data from the stream fails or the class can't be instrumented
 */
public byte[] instrument(final InputStream input,final String name) throws IOException {
  final byte[] bytes;
  try {
    bytes=InputStreams.readFully(input);
  }
 catch (  final IOException e) {
    throw instrumentError(name,e);
  }
  return instrument(bytes,name);
}
