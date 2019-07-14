@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!FUTURES_GET_CHECKED_MATCHER.matches(tree,state)) {
    return NO_MATCH;
  }
  if (PASSED_RUNTIME_EXCEPTION_TYPE.matches(tree,state)) {
    return describeUncheckedExceptionTypeMatch(tree,SuggestedFix.builder().replace(tree,"getUnchecked(" + state.getSourceForNode(tree.getArguments().get(0)) + ")").addStaticImport(Futures.class.getName() + ".getUnchecked").build());
  }
  if (PASSED_TYPE_WITHOUT_USABLE_CONSTRUCTOR.matches(tree,state)) {
    return describeNoValidConstructorMatch(tree);
  }
  return NO_MATCH;
}
