@Override public Choice<State<JCTypeCast>> visitTypeCast(final TypeCastTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyExpression(node.getExpression(),s),expr -> maker().TypeCast((JCTree)node.getType(),expr));
}
