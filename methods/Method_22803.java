/** 
 * Returns the mark position. This will be the opposite selection bound to the caret position.
 * @see #getCaretPosition()
 */
public final int getMarkPosition(){
  return (biasLeft ? selectionEnd : selectionStart);
}
