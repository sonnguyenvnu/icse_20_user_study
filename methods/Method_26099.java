@Override public Description matchMethod(MethodTree tree,VisitorState state){
  Symbol sym=ASTHelpers.getSymbol(tree);
  if (sym.isStatic()) {
    return Description.NO_MATCH;
  }
  if (ASTHelpers.hasAnnotation(sym,Override.class,state)) {
    return Description.NO_MATCH;
  }
  MethodSymbol override=getFirstOverride(sym,state.getTypes());
  if (override == null) {
    return Description.NO_MATCH;
  }
  if (ASTHelpers.hasAnnotation(override,Deprecated.class,state)) {
    return Description.NO_MATCH;
  }
  return buildDescription(tree).addFix(SuggestedFix.prefixWith(tree,"@Override ")).setMessage(String.format("%s %s method in %s; expected @Override",sym.getSimpleName(),override.enclClass().isInterface() || override.getModifiers().contains(Modifier.ABSTRACT) ? "implements" : "overrides",override.enclClass().getSimpleName())).build();
}
