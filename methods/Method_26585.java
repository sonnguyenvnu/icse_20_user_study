@Override public Choice<State<JCLambda>> visitLambdaExpression(final LambdaExpressionTree node,State<?> state){
  return chooseSubtrees(state,s -> unify(node.getBody(),s),body -> maker().Lambda(List.convert(JCVariableDecl.class,(List<? extends VariableTree>)node.getParameters()),body));
}
