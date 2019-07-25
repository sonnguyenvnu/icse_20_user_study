@Override public boolean met(SNode node){
  if (node == null) {
    if (myTolerateNull) {
      return false;
    }
    throw new NullPointerException();
  }
  for (  SAbstractConcept c : myConcepts) {
    if (node.getConcept().isSubConceptOf(c)) {
      return true;
    }
  }
  return false;
}
