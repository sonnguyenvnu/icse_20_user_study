public void select(Node node){
  String[] lines=getLines();
  if (node.getBeginLine() >= 0) {
    setSelectionStart(getPosition(lines,node.getBeginLine(),node.getBeginColumn()));
    setSelectionEnd(getPosition(lines,node.getEndLine(),node.getEndColumn()) + 1);
  }
  requestFocus();
}
