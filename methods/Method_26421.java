@Override public Description matchVariable(VariableTree tree,VisitorState state){
  if (tree.getInitializer() != null) {
    check(tree.getName().toString(),tree.getInitializer(),state);
  }
  return ANY_MATCHES_WERE_ALREADY_REPORTED;
}
