@Override public Choice<State<JCNewArray>> visitNewArray(final NewArrayTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyExpressions(node.getDimensions(),s),s -> unifyExpressions(node.getInitializers(),s),(dims,inits) -> maker().NewArray((JCExpression)node.getType(),dims,inits));
}
