/** 
 * Select a line in the current tab.
 * @param lineIdx 0-based line number
 */
public void selectLine(int lineIdx){
  setSelection(getLineStartOffset(lineIdx),getLineStopOffset(lineIdx));
}
