/** 
 * Reads the contents of ::cue() and returns it as a string.
 */
private static String readCueTarget(ParsableByteArray input){
  int position=input.getPosition();
  int limit=input.limit();
  boolean cueTargetEndFound=false;
  while (position < limit && !cueTargetEndFound) {
    char c=(char)input.data[position++];
    cueTargetEndFound=c == ')';
  }
  return input.readString(--position - input.getPosition()).trim();
}
