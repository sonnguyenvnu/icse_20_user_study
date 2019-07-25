/** 
 * Runs the recorder on the specified file. <p>This method will count on the  {@link org.xml.sax.InputSource} to guess the correct encoding.
 * @param file The file to process.
 * @return The recorded sequence of events.
 * @throws LoadingException If thrown whilst parsing.
 * @throws IOException      Should I/O error occur.
 */
public EventSequence process(File file) throws LoadingException, IOException {
  BufferedReader reader=new BufferedReader(new FileReader(file));
  String line=reader.readLine();
  int count=0;
  EventSequence seq=new EventSequence();
  while (line != null) {
    seq.addEvent(new LineEvent(line,++count));
    line=reader.readLine();
  }
  reader.close();
  return seq;
}
