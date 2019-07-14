private static boolean isProtoRepeatedFieldCountMethod(ExpressionTree tree,VisitorState state){
  if (!PROTO_METHOD_NAMED_GET_COUNT.matches(tree,state)) {
    return false;
  }
  MethodSymbol methodCallSym=getSymbol((MethodInvocationTree)tree);
  if (methodCallSym == null) {
    return false;
  }
  Scope protoClassMembers=methodCallSym.owner.members();
  java.util.regex.Matcher getCountRegexMatcher=PROTO_COUNT_METHOD_PATTERN.matcher(methodCallSym.getSimpleName().toString());
  if (!getCountRegexMatcher.matches()) {
    return false;
  }
  String fieldName=getCountRegexMatcher.group(1);
  return protoClassMembers.findFirst(state.getName("get" + fieldName + "List")) != null;
}
