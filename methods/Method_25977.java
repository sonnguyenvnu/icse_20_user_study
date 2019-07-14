@Override public final Description matchAnnotation(AnnotationTree annotationTree,VisitorState state){
  Tree modified=getCurrentlyAnnotatedNode(state);
  if (SCOPE_ANNOTATION_MATCHER.matches(annotationTree,state) && modified instanceof ClassTree && !IS_DAGGER_COMPONENT.matches((ClassTree)modified,state) && INTERFACE_AND_ABSTRACT_TYPE_MATCHER.matches((ClassTree)modified,state)) {
    return describeMatch(annotationTree,SuggestedFix.delete(annotationTree));
  }
  return Description.NO_MATCH;
}
