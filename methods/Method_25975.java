@Override public Description matchClass(ClassTree tree,VisitorState state){
  if (IS_QUALIFIER_WITH_TARGET.matches(tree,state)) {
    MultiMatchResult<AnnotationTree> targetAnnotation=HAS_TARGET_ANNOTATION.multiMatchResult(tree,state);
    if (targetAnnotation.matches()) {
      AnnotationTree annotationTree=targetAnnotation.onlyMatchingNode();
      Target target=ASTHelpers.getAnnotation(tree,Target.class);
      if (hasTypeUseOrTypeParameter(target)) {
        return describeMatch(annotationTree,removeTypeUse(target,annotationTree));
      }
    }
  }
  return Description.NO_MATCH;
}
