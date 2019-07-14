/** 
 * @return whether or not {@param text} should be debounced because it's the same as thecurrent target text of this column manager.
 */
boolean shouldDebounceText(char[] text){
  final int newTextSize=text.length;
  if (newTextSize == mRxTickerColumns.size()) {
    for (int i=0; i < newTextSize; i++) {
      if (text[i] != mRxTickerColumns.get(i).getTargetChar()) {
        return false;
      }
    }
    return true;
  }
  return false;
}
