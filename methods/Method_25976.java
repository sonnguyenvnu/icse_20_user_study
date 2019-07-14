private boolean hasTypeUseOrTypeParameter(Target targetAnnotation){
  return targetAnnotation != null && !Collections.disjoint(FORBIDDEN_ELEMENT_TYPES,Arrays.asList(targetAnnotation.value()));
}
