/** 
 * Runs this recorder on the specified string.
 * @param text The text string to process.
 * @return The recorded sequence of events.
 * @throws LoadingException If thrown whilst parsing.
 * @throws IOException      Should I/O error occur.
 */
public EventSequence process(String text) throws LoadingException, IOException {
  BufferedReader reader=new BufferedReader(new StringReader(text));
  String line=reader.readLine();
  int count=0;
  EventSequence seq=new EventSequence();
  while (line != null) {
    seq.addEvent(new LineEvent(line,++count));
    line=reader.readLine();
  }
  return seq;
}
