@Override public void visit(TextSpanElement ele){
  StylablePhrase phrase=document.createPhrase(currentContainer);
  applyStyles(ele,phrase);
  addITextContainer(ele,phrase);
}
