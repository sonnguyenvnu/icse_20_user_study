@Override public boolean test(@Nullable SAbstractConcept concept){
  if (myParentNode == null) {
    return true;
  }
  if (concept == null) {
    return true;
  }
  return (myContainmentLink == null || ModelConstraints.canBeParent(myParentNode,concept,myContainmentLink,null)) && ModelConstraints.canBeAncestor(myParentNode,concept,myContainmentLink,null);
}
