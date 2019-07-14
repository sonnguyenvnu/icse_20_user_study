@Override public Description matchAnnotatedType(AnnotatedTypeTree tree,VisitorState state){
  Type type=ASTHelpers.getType(tree);
  return check(type,tree.getAnnotations());
}
