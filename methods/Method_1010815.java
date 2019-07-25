/** 
 * This method should be called on entering/exiting the cell (from {@link jetbrains.mps.openapi.editor.cells.EditorCell#setSelected(boolean)} method).<p> It will show/hide the caret & suppress caret blinking for some small period.
 */
public void touch(boolean showCaret){
  myChangeCaretTimeStamp=System.currentTimeMillis();
  myCaretIsVisible=showCaret;
}
