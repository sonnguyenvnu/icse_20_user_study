/** 
 * Set the cursor to the end of a line.
 * @param lineIdx 0-based line number
 */
public void cursorToLineEnd(int lineIdx){
  setSelection(getLineStopOffset(lineIdx),getLineStopOffset(lineIdx));
}
