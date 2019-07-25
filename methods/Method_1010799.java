@Override public void deactivate(){
  super.deactivate();
  mySelectionStart=getEditorCellLabel().getSelectionStart();
  mySelectionEnd=getEditorCellLabel().getSelectionEnd();
  myNonTrivialSelection=mySelectionStart != mySelectionEnd;
  getEditorCellLabel().deselectAll();
}
