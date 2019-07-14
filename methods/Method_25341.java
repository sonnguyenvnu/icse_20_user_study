@Override public boolean matches(AnnotationTree annotationTree,VisitorState state){
  return AnnotationMatcherUtils.getArgument(annotationTree,name) == null;
}
