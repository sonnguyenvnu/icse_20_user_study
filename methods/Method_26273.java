@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (ASSERTION.matches(state.getPath().getParentPath().getLeaf(),state)) {
    return NO_MATCH;
  }
  List<? extends ExpressionTree> args=tree.getArguments();
  ExpressionTree toReplace;
  if (INSTANCE_MATCHER.matches(tree,state)) {
    toReplace=args.get(0);
  }
 else   if (STATIC_MATCHER.matches(tree,state)) {
    if (args.get(0).getKind() == Kind.IDENTIFIER && args.get(1).getKind() != Kind.IDENTIFIER) {
      toReplace=args.get(0);
    }
 else {
      toReplace=args.get(1);
    }
  }
 else {
    return NO_MATCH;
  }
  Description.Builder description=buildDescription(tree);
  Fix fix=fieldFix(toReplace,state);
  if (fix != null) {
    description.addFix(fix);
  }
  return description.build();
}
