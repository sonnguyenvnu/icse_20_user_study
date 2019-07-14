private static boolean hasCirvAnnotation(ExpressionTree tree,VisitorState state){
  Symbol untypedSymbol=getSymbol(tree);
  if (!(untypedSymbol instanceof MethodSymbol)) {
    return false;
  }
  MethodSymbol sym=(MethodSymbol)untypedSymbol;
  if (ASTHelpers.hasAnnotation(sym,CanIgnoreReturnValue.class,state)) {
    return true;
  }
  return ASTHelpers.findSuperMethods(sym,state.getTypes()).stream().anyMatch(superSym -> hasAnnotation(superSym,CanIgnoreReturnValue.class,state) && superSym.getReturnType().tsym.equals(sym.getReturnType().tsym));
}
