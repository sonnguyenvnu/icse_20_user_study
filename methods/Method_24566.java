public String getHiddenBeforeString(){
  antlr.CommonHiddenStreamToken child=null, parent=hiddenBefore;
  if (parent == null) {
    return "";
  }
  do {
    child=parent;
    parent=child.getHiddenBefore();
  }
 while (parent != null);
  StringBuilder hiddenBeforeString=new StringBuilder(100);
  for (CommonHiddenStreamToken t=child; t != null; t=t.getHiddenAfter()) {
    hiddenBeforeString.append(t.getText());
  }
  return hiddenBeforeString.toString();
}
