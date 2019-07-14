@Override public Choice<Unifier> visitIntersectionType(IntersectionTypeTree node,Unifier unifier){
  return unifyList(unifier,getBounds(),node.getBounds());
}
