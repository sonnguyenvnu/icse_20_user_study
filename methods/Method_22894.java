public void insertText(String what){
  startCompoundEdit();
  int caret=getCaretOffset();
  setSelection(caret,caret);
  textarea.setSelectedText(what);
  stopCompoundEdit();
}
