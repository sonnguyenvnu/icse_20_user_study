@Nullable private static Symbol tryGetSymbol(Tree tree){
  if (tree instanceof JCIdent) {
    return ((JCIdent)tree).sym;
  }
  if (tree instanceof JCFieldAccess) {
    return ((JCFieldAccess)tree).sym;
  }
  if (tree instanceof JCVariableDecl) {
    return ((JCVariableDecl)tree).sym;
  }
  return null;
}
