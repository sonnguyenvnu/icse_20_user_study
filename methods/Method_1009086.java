/** 
 * Runs the recorder on the specified file. <p>This method will count on the  {@link InputSource} to guess the correct encoding.
 * @param file The file to process.
 * @return The recorded sequence of events.
 * @throws LoadingException If thrown while parsing.
 * @throws IOException      Should I/O error occur.
 */
public EventSequence process(File file) throws LoadingException, IOException {
  InputStream in=new BufferedInputStream(new FileInputStream(file));
  EventSequence seq=null;
  seq=process(new InputSource(in));
  in.close();
  in=null;
  return seq;
}
