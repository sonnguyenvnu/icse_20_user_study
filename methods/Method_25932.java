private static boolean refersToFieldNamedActual(ExpressionTree tree){
  Symbol symbol=getSymbol(tree);
  return symbol != null && symbol.getKind().isField() && symbol.getSimpleName().contentEquals("actual");
}
