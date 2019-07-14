private SuggestedFix.Builder replaceWithSplitter(SuggestedFix.Builder fix,MethodInvocationTree tree,String methodAndArgument,VisitorState state,String splitMethod,boolean mutableList){
  ExpressionTree receiver=ASTHelpers.getReceiver(tree);
  if (mutableList) {
    fix.addImport("java.util.ArrayList");
  }
  return fix.addImport("com.google.common.base.Splitter").prefixWith(receiver,String.format("%sSplitter.%s.%s(",(mutableList ? "new ArrayList<>(" : ""),methodAndArgument,splitMethod)).replace(state.getEndPosition(receiver),state.getEndPosition(tree),(mutableList ? ")" : "") + ")");
}
