/** 
 * Sets the selection start. The new selection will be the new selection start and the old selection end.
 * @param selectionStart The selection start
 * @see #select(int,int)
 */
public final void setSelectionStart(int selectionStart){
  select(selectionStart,selectionEnd);
}
