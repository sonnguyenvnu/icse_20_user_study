@Override protected boolean matchArgument(ExpressionTree tree,VisitorState state){
  if (!ASTHelpers.isSubtype(ASTHelpers.getType(tree),state.getTypeFromString("java.lang.Number"),state)) {
    return false;
  }
  Symbol sym=ASTHelpers.getSymbol(tree);
  if (sym instanceof Symbol.VarSymbol && isFinal(sym) && sym.isStatic()) {
    return false;
  }
  return true;
}
