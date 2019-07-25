@Override public void visit(TextListElement ele){
  currentListLevel++;
  StylableList list=document.createList(currentContainer,currentListLevel);
  applyStyles(ele,list);
  Boolean continueNumbering=ele.getTextContinueNumberingAttribute();
  if (Boolean.TRUE.equals(continueNumbering) && previousList != null && previousList.getLastStyleApplied() != null && list.getLastStyleApplied() != null && previousList.getLastStyleApplied().getStyleName() != null && previousList.getLastStyleApplied().getStyleName().equals(list.getLastStyleApplied().getStyleName())) {
    list.setFirst(previousList.getIndex());
  }
  addITextContainer(ele,list);
  currentListLevel--;
  previousList=list;
}
