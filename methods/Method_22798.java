/** 
 * Returns the offset where the selection ends on the specified line.
 */
public int getSelectionStop(int line){
  if (line == selectionEndLine)   return selectionEnd;
 else   return getLineStopOffset(line) - 1;
}
