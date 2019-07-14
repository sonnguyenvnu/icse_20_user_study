@Override public Description matchAnnotation(AnnotationTree annotationTree,VisitorState state){
  if (injectOrAssistedInjectMatcher.matches(annotationTree,state)) {
    Tree treeWithAnnotation=state.getPath().getParentPath().getParentPath().getLeaf();
    if (ASTHelpers.getSymbol(treeWithAnnotation).isConstructor() && hasInjectAnnotation().matches(treeWithAnnotation,state) && HAS_ASSISTED_INJECT_MATCHER.matches((MethodTree)treeWithAnnotation,state)) {
      return describeMatch(annotationTree,SuggestedFix.delete(annotationTree));
    }
  }
  return Description.NO_MATCH;
}
