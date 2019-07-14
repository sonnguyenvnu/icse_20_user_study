@Override public Choice<Unifier> visitUnionType(UnionTypeTree node,Unifier unifier){
  return unifyList(unifier,getTypeAlternatives(),node.getTypeAlternatives());
}
