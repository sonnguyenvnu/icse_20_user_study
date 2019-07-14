@Override public Description matchLambdaExpression(LambdaExpressionTree tree,VisitorState state){
  for (  VariableTree formalParam : tree.getParameters()) {
    if (hasCompileTimeConstantAnnotation(state,ASTHelpers.getSymbol(formalParam))) {
      return buildDescription(tree).setMessage("Lambda expressions with @CompileTimeConstant parameters are not supported.").build();
    }
  }
  return Description.NO_MATCH;
}
