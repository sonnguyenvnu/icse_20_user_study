@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!IGNORING.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  List<? extends ExpressionTree> arguments=tree.getArguments();
  ImmutableSet<TypeSymbol> types=arguments.stream().map(t -> protoType(t,state)).filter(Optional::isPresent).map(Optional::get).collect(toImmutableSet());
  if (types.size() > 1) {
    return describeMatch(tree);
  }
  if (types.size() != 1) {
    return Description.NO_MATCH;
  }
  TypeSymbol type=getOnlyElement(types);
  for (ExpressionTree receiver=getReceiver(tree); receiver instanceof MethodInvocationTree; receiver=getReceiver(receiver)) {
    if (ASSERT_THAT.matches(receiver,state)) {
      return validateReceiver(tree,(MethodInvocationTree)receiver,type,state);
    }
  }
  return Description.NO_MATCH;
}
