@Override public Description matchAnnotation(AnnotationTree annotationTree,VisitorState state){
  if (IS_APPLICATION_OF_JAVAX_INJECT.matches(annotationTree,state)) {
    if (isFinalField(getSymbol(state.getPath().getParentPath().getParentPath().getLeaf()))) {
      return describeMatch(annotationTree,SuggestedFix.delete(annotationTree));
    }
  }
  return Description.NO_MATCH;
}
