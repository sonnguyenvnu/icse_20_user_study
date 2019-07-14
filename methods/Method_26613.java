@Override public Choice<Unifier> visitAnnotatedType(AnnotatedTypeTree node,Unifier unifier){
  return unifyList(unifier,getAnnotations(),node.getAnnotations()).thenChoose(unifications(getUnderlyingType(),node.getUnderlyingType()));
}
