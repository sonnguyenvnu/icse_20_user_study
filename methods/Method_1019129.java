/** 
 * clear markdown format
 */
public void clear(){
  removeTextChangedListener(mEditTextWatcher);
  Editable editable=getText();
  int selectionEnd=getSelectionEnd();
  int selectionStart=getSelectionStart();
  setText(editable.toString());
  setSelection(selectionStart,selectionEnd);
}
