@Override public Description matchClass(ClassTree tree,VisitorState state){
  Name simpleName=((JCClassDecl)tree).getSimpleName();
  return check(tree,simpleName,state);
}
