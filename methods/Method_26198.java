private static boolean literalIsFormatMethodArg(LiteralTree tree,MethodInvocationTree methodInvocationTree,VisitorState state){
  MethodSymbol symbol=ASTHelpers.getSymbol(methodInvocationTree);
  if (ASTHelpers.hasAnnotation(symbol,FormatMethod.class,state)) {
    int indexOfParam=findIndexOfFormatStringParameter(state,symbol);
    if (indexOfParam != -1) {
      List<? extends ExpressionTree> args=methodInvocationTree.getArguments();
      return args.size() > indexOfParam && args.get(indexOfParam) == tree;
    }
  }
  return false;
}
