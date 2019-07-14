@Override public Description matchVariable(VariableTree tree,VisitorState state){
  ExpressionTree initializer=stripNullCheck(tree.getInitializer(),state);
  Tree parent=state.getPath().getParentPath().getLeaf();
  if (initializer == null || initializer.getKind() != MEMBER_SELECT || parent.getKind() != CLASS || !tree.getModifiers().getFlags().contains(STATIC)) {
    return Description.NO_MATCH;
  }
  MemberSelectTree rhs=(MemberSelectTree)initializer;
  Symbol rhsClass=ASTHelpers.getSymbol(rhs.getExpression());
  Symbol lhsClass=ASTHelpers.getSymbol(parent);
  if (rhsClass != null && lhsClass != null && rhsClass.equals(lhsClass) && rhs.getIdentifier().contentEquals(tree.getName())) {
    return describeForVarDecl(tree,state);
  }
  return Description.NO_MATCH;
}
