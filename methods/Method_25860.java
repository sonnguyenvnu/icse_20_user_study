@Override public Description matchMethodInvocation(MethodInvocationTree tree,final VisitorState state){
  if (!METHOD_MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  Set<Object> keySet=new HashSet<>();
  for (  ExpressionTree expr : tree.getArguments()) {
    if (!(expr instanceof MethodInvocationTree)) {
      continue;
    }
    if (!ENTRY_MATCHER.matches(expr,state)) {
      continue;
    }
    MethodInvocationTree entryInvocation=(MethodInvocationTree)expr;
    Object key=ASTHelpers.constValue(entryInvocation.getArguments().get(0));
    if (key == null) {
      continue;
    }
    if (!keySet.add(key)) {
      return buildDescription(tree).setMessage(String.format("duplicate key '%s'; Map#ofEntries will throw an IllegalArgumentException",key)).build();
    }
  }
  return Description.NO_MATCH;
}
