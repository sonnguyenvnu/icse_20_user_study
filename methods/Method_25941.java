@Override public final Description matchAnnotation(AnnotationTree annotationTree,VisitorState state){
  if (!IS_APPLICATION_OF_AT_INJECT.matches(annotationTree,state)) {
    return Description.NO_MATCH;
  }
  Tree annotatedTree=getCurrentlyAnnotatedNode(state);
  if (!annotatedTree.getKind().equals(METHOD) || !methodIsConstructor().matches((MethodTree)annotatedTree,state)) {
    return Description.NO_MATCH;
  }
  ClassTree classTree=findEnclosingNode(state.getPath(),ClassTree.class);
  ImmutableList<Tree> potentiallyAnnotatedTrees=ImmutableList.<Tree>builder().add(classTree).addAll(getConstructors(classTree)).build();
  for (  Tree potentiallyAnnotatedTree : potentiallyAnnotatedTrees) {
    if (HAS_AUTO_FACTORY_ANNOTATION.matches(potentiallyAnnotatedTree,state) && (potentiallyAnnotatedTree.getKind().equals(CLASS) || potentiallyAnnotatedTree.equals(annotatedTree))) {
      return describeMatch(annotationTree,SuggestedFix.delete(annotationTree));
    }
  }
  return Description.NO_MATCH;
}
