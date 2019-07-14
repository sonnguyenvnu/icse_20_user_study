@Override public Description matchMethodInvocation(MethodInvocationTree methodInvocationTree,VisitorState state){
  String threadString;
  if (methodInvocationTree.getMethodSelect() instanceof MemberSelectTree) {
    threadString=state.getSourceForNode(((MemberSelectTree)methodInvocationTree.getMethodSelect()).getExpression());
  }
 else {
    threadString="this";
  }
  if (!methodInvocationTree.getArguments().isEmpty()) {
    return Description.NO_MATCH;
  }
  if (!MATCH_THREAD_JOIN.matches(methodInvocationTree,state)) {
    return Description.NO_MATCH;
  }
  TreePath tryTreePath=ASTHelpers.findPathFromEnclosingNodeToTopLevel(state.getPath(),TryTree.class);
  if (tryTreePath == null) {
    return Description.NO_MATCH;
  }
  WhileLoopTree pathToLoop=ASTHelpers.findEnclosingNode(tryTreePath,WhileLoopTree.class);
  boolean hasWhileLoopOneStatement=false;
  if (pathToLoop != null) {
    Tree statements=pathToLoop.getStatement();
    if (statements instanceof BlockTree && ((BlockTree)statements).getStatements().size() == 1) {
      hasWhileLoopOneStatement=true;
    }
  }
  TryTree tryTree=(TryTree)tryTreePath.getLeaf();
  if (hasOtherInvocationsOrAssignments(methodInvocationTree,tryTree,state)) {
    return Description.NO_MATCH;
  }
  if (tryTree.getFinallyBlock() != null) {
    return Description.NO_MATCH;
  }
  Type interruptedType=state.getSymtab().interruptedExceptionType;
  for (  CatchTree tree : tryTree.getCatches()) {
    Type typeSym=getType(tree.getParameter().getType());
    if (ASTHelpers.isCastable(typeSym,interruptedType,state)) {
      if (tree.getBlock().getStatements().stream().allMatch(s -> s instanceof EmptyStatementTree)) {
        SuggestedFix.Builder fix=SuggestedFix.builder();
        String uninterruptibles=SuggestedFixes.qualifyType(state,fix,"com.google.common.util.concurrent.Uninterruptibles");
        fix.replace(hasWhileLoopOneStatement ? pathToLoop : tryTree,String.format("%s.joinUninterruptibly(%s);",uninterruptibles,threadString));
        return describeMatch(methodInvocationTree,fix.build());
      }
    }
  }
  return Description.NO_MATCH;
}
