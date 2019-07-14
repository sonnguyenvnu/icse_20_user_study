private static Number getIntegralConstant(Tree node){
  Number number=ASTHelpers.constValue(node,Number.class);
  if (number instanceof Integer || number instanceof Long) {
    return number;
  }
  return null;
}
