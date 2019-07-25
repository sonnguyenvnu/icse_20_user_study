@Override public boolean test(@Nullable SAbstractConcept concept){
  return concept == null || ModelConstraints.canBeChild(myParentNode,concept,myContainmentLink,null);
}
