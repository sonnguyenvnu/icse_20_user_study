/** 
 * Parses the event body of the subtitle.
 * @param data A {@link ParsableByteArray} from which the body should be read.
 * @param cues A list to which parsed cues will be added.
 * @param cueTimesUs An array to which parsed cue timestamps will be added.
 */
private void parseEventBody(ParsableByteArray data,List<Cue> cues,LongArray cueTimesUs){
  String currentLine;
  while ((currentLine=data.readLine()) != null) {
    if (!haveInitializationData && currentLine.startsWith(FORMAT_LINE_PREFIX)) {
      parseFormatLine(currentLine);
    }
 else     if (currentLine.startsWith(DIALOGUE_LINE_PREFIX)) {
      parseDialogueLine(currentLine,cues,cueTimesUs);
    }
  }
}
