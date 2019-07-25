@Override public void activate(){
  myEditorCell.setSelected(true);
  if (myActivateUsingRelativeCaretX) {
    setRelativeCaretX(myEditorCell,getCaretXRelative());
  }
 else {
    myEditorCell.setCaretX(getCaretX());
    myActivateUsingRelativeCaretX=true;
  }
  myActive=true;
}
