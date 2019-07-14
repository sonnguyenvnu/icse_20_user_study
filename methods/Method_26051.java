private boolean methodCallsMeetConditions(Symbol sym,VisitorState state){
  ImmutableMultimap<String,MethodInvocationTree> methodCallMap=methodCallsForSymbol(sym,getTopLevelClassTree(state));
  if (methodCallMap.isEmpty()) {
    return true;
  }
  for (  MethodInvocationTree methodInvocationTree : methodCallMap.values()) {
    if (methodInvocationTree.getArguments().stream().filter(a -> Kind.LAMBDA_EXPRESSION.equals(a.getKind())).filter(a -> hasFunctionAsArg(a,state)).noneMatch(a -> isFunctionArgSubtypeOf(a,0,state.getTypeFromString(JAVA_LANG_NUMBER),state) || isFunctionArgSubtypeOf(a,1,state.getTypeFromString(JAVA_LANG_NUMBER),state))) {
      return false;
    }
  }
  return true;
}
