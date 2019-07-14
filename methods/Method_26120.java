@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!MATCHER.matches(tree,state)) {
    return NO_MATCH;
  }
  if (state.getTypes().closure(ASTHelpers.getSymbol(tree).enclClass().asType()).stream().anyMatch(s -> s.asElement().packge().getQualifiedName().toString().startsWith("java.util.concurrent"))) {
    return NO_MATCH;
  }
  if (blockEndsInBreakOrReturn(state)) {
    return NO_MATCH;
  }
  ExpressionTree collection=getReceiver(tree);
  if (collection == null) {
    return NO_MATCH;
  }
  if (!enclosingLoop(state.getPath(),collection)) {
    return NO_MATCH;
  }
  return describeMatch(tree);
}
