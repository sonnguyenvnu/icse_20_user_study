private static String getMessageOrFormat(MethodInvocationTree tree,VisitorState state){
  if (tree.getArguments().size() > 1) {
    return "String.format(" + state.getSourceCode().subSequence(((JCTree)tree.getArguments().get(0)).getStartPosition(),state.getEndPosition(Iterables.getLast(tree.getArguments()))) + ")";
  }
  return state.getSourceForNode(getOnlyElement(tree.getArguments()));
}
