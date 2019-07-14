private static boolean requiresJavadoc(Tree tree,VisitorState state){
  if (state.errorProneOptions().isTestOnlyTarget()) {
    return false;
  }
  Symbol symbol=getSymbol(tree);
  if (symbol instanceof MethodSymbol && !findSuperMethods((MethodSymbol)symbol,state.getTypes()).isEmpty()) {
    return false;
  }
  return symbol != null && (symbol.getModifiers().contains(Modifier.PUBLIC) || symbol.getModifiers().contains(Modifier.PROTECTED));
}
