@Nullable private static VarSymbol asSymbol(ExpressionTree tree){
  Symbol symbol=ASTHelpers.getSymbol(tree);
  return symbol instanceof VarSymbol ? (VarSymbol)symbol : null;
}
