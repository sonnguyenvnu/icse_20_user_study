@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  RestrictedApi annotation=ASTHelpers.getAnnotation(tree,RestrictedApi.class);
  if (annotation != null) {
    return checkRestriction(annotation,tree,state);
  }
  MethodSymbol methSymbol=ASTHelpers.getSymbol(tree);
  if (methSymbol == null) {
    return Description.NO_MATCH;
  }
  Optional<MethodSymbol> superWithRestrictedApi=ASTHelpers.findSuperMethods(methSymbol,state.getTypes()).stream().filter((t) -> ASTHelpers.hasAnnotation(t,RestrictedApi.class,state)).findFirst();
  if (!superWithRestrictedApi.isPresent()) {
    return Description.NO_MATCH;
  }
  return checkRestriction(ASTHelpers.getAnnotation(superWithRestrictedApi.get(),RestrictedApi.class),tree,state);
}
