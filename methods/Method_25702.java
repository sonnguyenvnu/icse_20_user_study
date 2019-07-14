/** 
 * Returns true if there are no other calls to this method which already have an actual parameter in the position we are moving this one too.
 */
@Override public boolean isAcceptableChange(Changes changes,Tree node,MethodSymbol symbol,VisitorState state){
  return findArgumentsForOtherInstances(symbol,node,state).stream().allMatch(arguments -> !anyArgumentsMatch(changes.changedPairs(),arguments));
}
