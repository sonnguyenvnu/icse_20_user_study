private static void fixCastingInvocations(List<MethodInvocationTree> castMethodInvocations,TreePath enclosingMethod,SuggestedFix.Builder fixBuilder,VisitorState state){
  for (  MethodInvocationTree castInvocation : castMethodInvocations) {
    ExpressionTree receiver=ASTHelpers.getReceiver(castInvocation);
    Type expressionType=ASTHelpers.getType(castInvocation);
    TreePath castPath=TreePath.getPath(enclosingMethod,castInvocation);
    if (castPath.getParentPath() != null && castPath.getParentPath().getLeaf().getKind() == Kind.EXPRESSION_STATEMENT) {
      fixBuilder.delete(castPath.getParentPath().getLeaf());
    }
 else {
      Type unboxedReceiverType=state.getTypes().unboxedType(ASTHelpers.getType(receiver));
      if (unboxedReceiverType.getTag() == expressionType.getTag()) {
        fixBuilder.replace(castInvocation,state.getSourceForNode(receiver));
      }
 else {
        fixBuilder.replace(castInvocation,String.format("(%s) %s",expressionType.tsym.getSimpleName(),state.getSourceForNode(receiver)));
      }
    }
  }
}
