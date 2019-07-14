/** 
 * Returns the caret line.
 */
public final int getCaretLine(){
  return (biasLeft ? selectionStartLine : selectionEndLine);
}
