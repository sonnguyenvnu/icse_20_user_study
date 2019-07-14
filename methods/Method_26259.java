/** 
 * Methods in  {@link java.util.function} are pure, and their returnvalues should not be discarded.
 */
private static boolean functionalMethod(ExpressionTree tree,VisitorState state){
  Symbol symbol=ASTHelpers.getSymbol(tree);
  return symbol instanceof MethodSymbol && ((MethodSymbol)symbol).owner.packge().getQualifiedName().contentEquals("java.util.function");
}
