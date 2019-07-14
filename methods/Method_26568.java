@Override public Choice<State<JCArrayAccess>> visitArrayAccess(final ArrayAccessTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyExpression(node.getExpression(),s),s -> unifyExpression(node.getIndex(),s),maker()::Indexed);
}
