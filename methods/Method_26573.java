@Override public Choice<State<JCNewClass>> visitNewClass(final NewClassTree node,State<?> state){
  if (node.getEnclosingExpression() != null || (node.getTypeArguments() != null && !node.getTypeArguments().isEmpty()) || node.getClassBody() != null) {
    return Choice.none();
  }
  return chooseSubtrees(state,s -> unifyExpression(node.getIdentifier(),s),s -> unifyExpressions(node.getArguments(),s),(ident,args) -> maker().NewClass(null,null,ident,args,null));
}
