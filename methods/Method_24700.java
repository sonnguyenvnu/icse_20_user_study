/** 
 * Set the cursor to the start of a line.
 * @param lineIdx 0-based line number
 */
public void cursorToLineStart(int lineIdx){
  setSelection(getLineStartOffset(lineIdx),getLineStartOffset(lineIdx));
}
