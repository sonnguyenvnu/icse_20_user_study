@Override public void activate(){
  super.activate();
  if (myNonTrivialSelection) {
    getEditorCellLabel().setSelectionStart(mySelectionStart);
    getEditorCellLabel().setSelectionEnd(mySelectionEnd);
  }
}
