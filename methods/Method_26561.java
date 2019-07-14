public Choice<? extends State<? extends JCTree>> unify(@Nullable Tree node,State<?> state){
  if (node instanceof ExpressionTree) {
    return unifyExpression((ExpressionTree)node,state);
  }
 else   if (node == null) {
    return Choice.of(state.<JCTree>withResult(null));
  }
 else {
    return node.accept(this,state);
  }
}
