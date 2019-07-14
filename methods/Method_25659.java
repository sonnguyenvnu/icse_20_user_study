/** 
 * Whether the given WakeLock may throw an unexpected RuntimeException when released. <p>Returns true if: 1) the given WakeLock was acquired with timeout, and 2) the given WakeLock is reference counted.
 */
private boolean wakelockMayThrow(Symbol wakelockSymbol,VisitorState state){
  ClassTree enclosingClass=getTopLevelClass(state);
  ImmutableMultimap<String,MethodInvocationTree> map=methodCallsForSymbol(wakelockSymbol,enclosingClass);
  return map.get("acquire").stream().anyMatch(m -> m.getArguments().size() == 1) && map.get("setReferenceCounted").stream().noneMatch(m -> Boolean.FALSE.equals(constValue(m.getArguments().get(0),Boolean.class)));
}
