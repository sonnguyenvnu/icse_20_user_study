/** 
 * Returns all the ways this placeholder invocation might unify with the specified tree: either by unifying the entire tree with an argument to the placeholder invocation, or by recursing on the subtrees.
 */
@SuppressWarnings("unchecked") public Choice<? extends State<? extends JCExpression>> unifyExpression(@Nullable ExpressionTree node,State<?> state){
  if (node == null) {
    return Choice.of(state.<JCExpression>withResult(null));
  }
  Choice<? extends State<? extends JCExpression>> tryBindArguments=tryBindArguments(node,state);
  if (!node.accept(FORBIDDEN_REFERENCE_VISITOR,state.unifier())) {
    return tryBindArguments.or((Choice)node.accept(this,state));
  }
 else {
    return tryBindArguments;
  }
}
