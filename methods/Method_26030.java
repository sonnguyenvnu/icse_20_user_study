@Override public Description matchMethodInvocation(MethodInvocationTree methodInvocationTree,VisitorState state){
  if (!ASSERT_EQUALS_MATCHER.matches(methodInvocationTree,state)) {
    return Description.NO_MATCH;
  }
  List<Type> argumentTypes=getArgumentTypesWithoutMessage(methodInvocationTree,state);
  if (canBeConvertedToJUnit4(state,argumentTypes)) {
    return Description.NO_MATCH;
  }
  Fix fix=addDeltaArgument(methodInvocationTree,state,argumentTypes);
  return describeMatch(methodInvocationTree,fix);
}
