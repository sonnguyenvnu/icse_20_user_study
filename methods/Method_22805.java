/** 
 * Sets the caret position. The new selection will consist of the caret position only (hence no text will be selected)
 * @param caret The caret position
 * @see #select(int,int)
 */
public final void setCaretPosition(int caret){
  select(caret,caret);
}
