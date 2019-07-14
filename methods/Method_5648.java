/** 
 * Positions the input right before the next event, and returns the kind of event found. Does not consume any data from such event, if any.
 * @return The kind of event found.
 */
private static int getNextEvent(ParsableByteArray parsableWebvttData){
  int foundEvent=EVENT_NONE;
  int currentInputPosition=0;
  while (foundEvent == EVENT_NONE) {
    currentInputPosition=parsableWebvttData.getPosition();
    String line=parsableWebvttData.readLine();
    if (line == null) {
      foundEvent=EVENT_END_OF_FILE;
    }
 else     if (STYLE_START.equals(line)) {
      foundEvent=EVENT_STYLE_BLOCK;
    }
 else     if (line.startsWith(COMMENT_START)) {
      foundEvent=EVENT_COMMENT;
    }
 else {
      foundEvent=EVENT_CUE;
    }
  }
  parsableWebvttData.setPosition(currentInputPosition);
  return foundEvent;
}
