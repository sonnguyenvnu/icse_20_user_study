@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  return checkRestriction(ASTHelpers.getAnnotation(tree,RestrictedApi.class),tree,state);
}
