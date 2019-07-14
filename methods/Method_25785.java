@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  MethodSymbol symbol=getSymbol(tree);
  if (!isIdentifierWithName(tree.getMethodSelect(),"this")) {
    return NO_MATCH;
  }
  callersToEvaluate.put(symbol,new Caller(tree,state));
  return evaluateCallers(symbol);
}
