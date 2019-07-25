/** 
 * Reset the extractor. User reset(CharSequence input) to update InputCharSequence attribute.
 */
public void reset(){
  CharSequence input=inputCharSequence();
  Preconditions.checkNotNull(input);
  updateInputCharSequence(input);
  clearAttributes();
  if (triggeringChar > 0) {
    boolean foundTriggeringChar=false;
    for (int i=0; i < input.length(); i++) {
      if (triggeringChar == input.charAt(i)) {
        foundTriggeringChar=true;
        break;
      }
    }
    if (!foundTriggeringChar) {
      matcher=null;
      return;
    }
  }
  if (regexPattern != null) {
    matcher=regexPattern.matcher(input);
  }
}
