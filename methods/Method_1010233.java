@Override public SNode resolve(SNode contextNode,@NotNull String refText){
  SNode result=null;
  for (  SNode n : SNodeOperations.getChildren(myNode,myLink)) {
    if (this.concept != null && !(n.getConcept().isSubConceptOf(concept))) {
      continue;
    }
    if (refText.equals(getName(n))) {
      if (result != null) {
        return null;
      }
      result=n;
    }
  }
  return result;
}
