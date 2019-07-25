@Override public void deactivate(){
  myActive=false;
  myEditorCell.setSelected(false);
  myCaretX=myEditorCell.getCaretX();
  myCaretXRelative=getRelativeCaretX(myEditorCell);
}
