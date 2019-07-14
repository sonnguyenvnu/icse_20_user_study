private static ImmutableList<Commented<ExpressionTree>> findCommentsForArguments(Tree tree,VisitorState state){
switch (tree.getKind()) {
case METHOD_INVOCATION:
    return Comments.findCommentsForArguments((MethodInvocationTree)tree,state);
case NEW_CLASS:
  return Comments.findCommentsForArguments((NewClassTree)tree,state);
default :
throw new IllegalArgumentException("Only MethodInvocationTree or NewClassTree is supported");
}
}
