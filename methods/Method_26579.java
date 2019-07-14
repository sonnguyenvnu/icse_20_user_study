@Override public Choice<State<JCLabeledStatement>> visitLabeledStatement(final LabeledStatementTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyStatement(node.getStatement(),s),stmt -> maker().Labelled((Name)node.getLabel(),stmt));
}
