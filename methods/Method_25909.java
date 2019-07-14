private static boolean isObjectReturningLambdaExpression(Tree tree,VisitorState state){
  if (!(tree instanceof LambdaExpressionTree)) {
    return false;
  }
  Type type=((JCLambda)tree).type;
  return functionalInterfaceReturnsObject(type,state) && !isWhitelistedInterfaceType(type,state);
}
