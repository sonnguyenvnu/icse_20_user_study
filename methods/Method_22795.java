/** 
 * Returns the offset where the selection starts on the specified line.
 */
public int getSelectionStart(int line){
  if (line == selectionStartLine)   return selectionStart;
 else   return getLineStartOffset(line);
}
