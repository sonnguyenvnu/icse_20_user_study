@Override public Choice<State<JCUnary>> visitUnary(UnaryTree node,State<?> state){
  final Tag tag=((JCUnary)node).getTag();
  return chooseSubtrees(state,s -> unifyExpression(node.getExpression(),s),expr -> maker().Unary(tag,expr)).condition(s -> !MUTATING_UNARY_TAGS.contains(tag) || !(s.result().getExpression() instanceof PlaceholderParamIdent));
}
