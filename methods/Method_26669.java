@Override public Choice<Unifier> visitLabeledStatement(LabeledStatementTree node,Unifier unifier){
  unifier.putBinding(key(),node.getLabel());
  return getStatement().unify(node.getStatement(),unifier);
}
