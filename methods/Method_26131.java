@Override public Description matchMethodInvocation(MethodInvocationTree t,VisitorState state){
  if (STREAM.matches(t,state) || PARALLELSTREAM.matches(t,state)) {
    int appropriateAmount=STREAM.matches(t,state) ? 1 : 0;
    SuggestedFix.Builder builder=SuggestedFix.builder();
    TreePath pathToMet=ASTHelpers.findPathFromEnclosingNodeToTopLevel(state.getPath(),MethodInvocationTree.class);
    int count=0;
    String toReplace="empty";
    while (pathToMet != null) {
      MethodInvocationTree methodInvocationTree=(MethodInvocationTree)pathToMet.getLeaf();
      if (methodInvocationTree.getArguments().stream().map(m -> m.toString()).anyMatch(m -> m.contains(t.toString()))) {
        break;
      }
      if (methodInvocationTree.getMethodSelect() instanceof MemberSelectTree) {
        MemberSelectTree memberSelectTree=(MemberSelectTree)methodInvocationTree.getMethodSelect();
        String memberSelectIdentifier=memberSelectTree.getIdentifier().toString();
        if (toReplace.equals("empty") && (memberSelectIdentifier.equals("parallel") || memberSelectIdentifier.equals("sequential"))) {
          toReplace=memberSelectIdentifier.equals("parallel") ? "parallel" : "sequential";
        }
        if (memberSelectIdentifier.equals(toReplace)) {
          int endOfExpression=state.getEndPosition(memberSelectTree.getExpression());
          builder.replace(endOfExpression,state.getEndPosition(methodInvocationTree),"");
          count++;
        }
      }
      pathToMet=ASTHelpers.findPathFromEnclosingNodeToTopLevel(pathToMet,MethodInvocationTree.class);
    }
    if (count > appropriateAmount) {
      if (appropriateAmount == 1) {
        builder.postfixWith(t,"." + toReplace + "()");
      }
      return describeMatch(t,builder.build());
    }
  }
  return Description.NO_MATCH;
}
