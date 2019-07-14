/** 
 * Parses a dialogue line.
 * @param dialogueLine The line to parse.
 * @param cues A list to which parsed cues will be added.
 * @param cueTimesUs An array to which parsed cue timestamps will be added.
 */
private void parseDialogueLine(String dialogueLine,List<Cue> cues,LongArray cueTimesUs){
  if (formatKeyCount == 0) {
    Log.w(TAG,"Skipping dialogue line before complete format: " + dialogueLine);
    return;
  }
  String[] lineValues=dialogueLine.substring(DIALOGUE_LINE_PREFIX.length()).split(",",formatKeyCount);
  if (lineValues.length != formatKeyCount) {
    Log.w(TAG,"Skipping dialogue line with fewer columns than format: " + dialogueLine);
    return;
  }
  long startTimeUs=SsaDecoder.parseTimecodeUs(lineValues[formatStartIndex]);
  if (startTimeUs == C.TIME_UNSET) {
    Log.w(TAG,"Skipping invalid timing: " + dialogueLine);
    return;
  }
  long endTimeUs=C.TIME_UNSET;
  String endTimeString=lineValues[formatEndIndex];
  if (!endTimeString.trim().isEmpty()) {
    endTimeUs=SsaDecoder.parseTimecodeUs(endTimeString);
    if (endTimeUs == C.TIME_UNSET) {
      Log.w(TAG,"Skipping invalid timing: " + dialogueLine);
      return;
    }
  }
  String text=lineValues[formatTextIndex].replaceAll("\\{.*?\\}","").replaceAll("\\\\N","\n").replaceAll("\\\\n","\n");
  cues.add(new Cue(text));
  cueTimesUs.add(startTimeUs);
  if (endTimeUs != C.TIME_UNSET) {
    cues.add(null);
    cueTimesUs.add(endTimeUs);
  }
}
