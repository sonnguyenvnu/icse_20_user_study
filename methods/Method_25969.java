@Override public final Description matchClass(ClassTree classTree,VisitorState state){
  if (ANNOTATION_WITH_SCOPE_AND_TARGET.matches(classTree,state)) {
    MultiMatchResult<AnnotationTree> targetAnnotation=HAS_TARGET_ANNOTATION.multiMatchResult(classTree,state);
    if (targetAnnotation.matches()) {
      AnnotationTree targetTree=targetAnnotation.onlyMatchingNode();
      Target target=getAnnotation(classTree,Target.class);
      if (target != null && !Arrays.asList(target.value()).containsAll(REQUIRED_ELEMENT_TYPES)) {
        return describeMatch(targetTree,replaceTargetAnnotation(target,targetTree));
      }
    }
  }
  return Description.NO_MATCH;
}
