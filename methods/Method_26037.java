/** 
 * Creates the fix to add a delta argument. 
 */
private Fix addDeltaArgument(MethodInvocationTree methodInvocationTree,VisitorState state,List<Type> argumentTypes){
  int insertionIndex=getDeltaInsertionIndex(methodInvocationTree,state);
  String deltaArgument=getDeltaArgument(state,argumentTypes);
  return SuggestedFix.replace(insertionIndex,insertionIndex,deltaArgument);
}
