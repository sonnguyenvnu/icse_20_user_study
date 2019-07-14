/** 
 * Returns the caret position. This will either be the selection start or the selection end, depending on which direction the selection was made in.
 */
public final int getCaretPosition(){
  return (biasLeft ? selectionStart : selectionEnd);
}
