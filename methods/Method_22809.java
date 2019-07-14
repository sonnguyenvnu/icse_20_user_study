/** 
 * Returns the selected text, or null if no selection is active.
 */
public final String getSelectedText(){
  if (selectionStart == selectionEnd) {
    return null;
  }
 else {
    return getText(selectionStart,selectionEnd - selectionStart);
  }
}
