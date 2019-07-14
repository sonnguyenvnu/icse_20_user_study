protected void addFixes(Description.Builder builder,BinaryTree tree,VisitorState state){
  ExpressionTree lhs=tree.getLeftOperand();
  ExpressionTree rhs=tree.getRightOperand();
  Optional<Fix> fixToReplaceOrStatement=inOrStatementWithEqualsCheck(state,tree);
  if (fixToReplaceOrStatement.isPresent()) {
    builder.addFix(fixToReplaceOrStatement.get());
    return;
  }
  if (ASTHelpers.constValue(lhs) == null && ASTHelpers.constValue(rhs) != null) {
    ExpressionTree tmp=lhs;
    lhs=rhs;
    rhs=tmp;
  }
  String prefix=tree.getKind() == Kind.NOT_EQUAL_TO ? "!" : "";
  String lhsSource=state.getSourceForNode(lhs);
  String rhsSource=state.getSourceForNode(rhs);
  Nullness nullness=getNullness(lhs,state);
  if (nullness != NONNULL) {
    if (state.isAndroidCompatible()) {
      builder.addFix(SuggestedFix.builder().replace(tree,String.format("%sObjects.equal(%s, %s)",prefix,lhsSource,rhsSource)).addImport("com.google.common.base.Objects").build());
    }
 else {
      builder.addFix(SuggestedFix.builder().replace(tree,String.format("%sObjects.equals(%s, %s)",prefix,lhsSource,rhsSource)).addImport("java.util.Objects").build());
    }
  }
  if (nullness != NULL) {
    builder.addFix(SuggestedFix.replace(tree,String.format("%s%s.equals(%s)",prefix,lhs instanceof BinaryTree ? String.format("(%s)",lhsSource) : lhsSource,rhsSource)));
  }
}
