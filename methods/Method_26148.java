@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!MATCHER.matches(tree,state)) {
    return NO_MATCH;
  }
  List<? extends ExpressionTree> arguments=tree.getArguments();
  Symtab syms=state.getSymtab();
  Types types=state.getTypes();
  if (types.isSameType(types.unboxedTypeOrType(getType(arguments.get(1))),syms.intType) && types.isSameType(types.unboxedTypeOrType(getType(arguments.get(0))),syms.charType)) {
    return describeMatch(tree,SuggestedFix.builder().replace(arguments.get(0),state.getSourceForNode(arguments.get(1))).replace(arguments.get(1),state.getSourceForNode(arguments.get(0))).build());
  }
  return NO_MATCH;
}
