private static Fix removeSubstringCall(MethodInvocationTree tree,VisitorState state){
  ExpressionTree originalString=ASTHelpers.getReceiver(tree);
  return SuggestedFix.replace(tree,state.getSourceForNode(originalString));
}
