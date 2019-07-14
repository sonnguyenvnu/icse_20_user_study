private Description check(Type type,List<? extends AnnotationTree> annotations){
  if (type == null) {
    return NO_MATCH;
  }
  if (!type.isPrimitive()) {
    return NO_MATCH;
  }
  AnnotationTree annotation=ASTHelpers.getAnnotationWithSimpleName(annotations,"Nullable");
  if (annotation == null) {
    return NO_MATCH;
  }
  return describeMatch(annotation,SuggestedFix.delete(annotation));
}
