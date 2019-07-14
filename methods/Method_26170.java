@Override public Description matchMethod(MethodTree tree,VisitorState state){
  MethodSymbol sym=ASTHelpers.getSymbol(tree);
  if (sym == null) {
    return NO_MATCH;
  }
  return check(sym.getReturnType(),tree.getModifiers().getAnnotations());
}
