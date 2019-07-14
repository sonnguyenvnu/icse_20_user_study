@Override public Description matchClass(ClassTree tree,VisitorState state){
  if (tree.getTypeParameters().isEmpty()) {
    return Description.NO_MATCH;
  }
  return findDuplicatesOf(tree,tree.getTypeParameters(),state);
}
