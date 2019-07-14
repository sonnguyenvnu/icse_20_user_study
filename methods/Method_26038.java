/** 
 * Gets the index of where to insert the delta argument. 
 */
private int getDeltaInsertionIndex(MethodInvocationTree methodInvocationTree,VisitorState state){
  JCTree lastArgument=(JCTree)Iterables.getLast(methodInvocationTree.getArguments());
  return state.getEndPosition(lastArgument);
}
