@Override public Description matchLiteral(LiteralTree tree,VisitorState state){
  if (tree.getValue() == null || !tree.getValue().equals("C++ destructor does not have public access")) {
    return NO_MATCH;
  }
  MethodTree enclosingMethodTree=ASTHelpers.findEnclosingNode(state.getPath(),MethodTree.class);
  Name name=enclosingMethodTree.getName();
  if (!name.contentEquals("delete")) {
    return NO_MATCH;
  }
  if (ENCLOSING_CLASS_HAS_FINALIZER.matches(enclosingMethodTree,state)) {
    return NO_MATCH;
  }
  return buildDescription(tree).build();
}
