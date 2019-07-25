@Override public Object next(){
  if (!matchTextFresh) {
    updateMatchText();
  }
  matchTextFresh=false;
  return matchText;
}
