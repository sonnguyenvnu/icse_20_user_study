@Override public void visit(TextTabElement ele){
  StylableTab tab=document.createTab(currentContainer,inTableOfContent);
  Style style=currentContainer.getLastStyleApplied();
  if (style != null) {
    tab.applyStyles(style);
  }
  addITextElement(tab);
}
