/** 
 * Returns a string containing the selector. The input is expected to have the form {@code ::cue(tag#id.class1.class2[voice="someone"]}, where every element is optional.
 * @param input From which the selector is obtained.
 * @return A string containing the target, empty string if the selector is universal(targets all cues) or null if an error was encountered.
 */
private static String parseSelector(ParsableByteArray input,StringBuilder stringBuilder){
  skipWhitespaceAndComments(input);
  if (input.bytesLeft() < 5) {
    return null;
  }
  String cueSelector=input.readString(5);
  if (!"::cue".equals(cueSelector)) {
    return null;
  }
  int position=input.getPosition();
  String token=parseNextToken(input,stringBuilder);
  if (token == null) {
    return null;
  }
  if (BLOCK_START.equals(token)) {
    input.setPosition(position);
    return "";
  }
  String target=null;
  if ("(".equals(token)) {
    target=readCueTarget(input);
  }
  token=parseNextToken(input,stringBuilder);
  if (!")".equals(token) || token == null) {
    return null;
  }
  return target;
}
