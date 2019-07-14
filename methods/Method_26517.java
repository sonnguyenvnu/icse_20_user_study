@Override public Description matchClass(ClassTree tree,VisitorState state){
  return check(tree,tree.getSimpleName());
}
