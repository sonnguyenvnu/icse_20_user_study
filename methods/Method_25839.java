private static SuggestedFix.Builder byteStringFix(MethodInvocationTree tree,ExpressionTree parent,VisitorState state,String prefix,String suffix){
  Tree parentReceiver=ASTHelpers.getReceiver(parent);
  Builder fix=SuggestedFix.builder();
  if (parentReceiver != null) {
    fix.replace(state.getEndPosition(parentReceiver),((JCTree)tree).getStartPosition(),"." + prefix);
  }
 else {
    fix.replace(((JCTree)parent).getStartPosition(),((JCTree)tree).getStartPosition(),prefix);
  }
  fix.replace(state.getEndPosition(ASTHelpers.getReceiver(tree)),state.getEndPosition(tree),suffix);
  return fix;
}
