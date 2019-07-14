@Override public Description matchClass(ClassTree classTree,VisitorState state){
  return checkDeprecatedAnnotation(classTree,state);
}
