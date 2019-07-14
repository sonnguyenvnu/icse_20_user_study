private static void insertPreservingSelection(EditText editText,int position,CharSequence text){
  int selectionStart=editText.getSelectionStart();
  int selectionEnd=editText.getSelectionEnd();
  Editable editable=editText.getText();
  editable.insert(position,text);
  boolean needPreserve=false;
  if (selectionStart == position) {
    needPreserve=true;
  }
 else {
    selectionStart=editText.getSelectionStart();
  }
  if (selectionEnd == position) {
    needPreserve=true;
  }
 else {
    selectionEnd=editText.getSelectionEnd();
  }
  if (needPreserve) {
    editText.setSelection(selectionStart,selectionEnd);
  }
}
