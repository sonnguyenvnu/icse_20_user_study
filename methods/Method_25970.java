/** 
 * Rewrite the annotation with static imports, adding TYPE and METHOD to the @Target annotation value (and reordering them to their declaration order in ElementType).
 */
private static Fix replaceTargetAnnotation(Target annotation,AnnotationTree targetAnnotationTree){
  Set<ElementType> types=EnumSet.copyOf(REQUIRED_ELEMENT_TYPES);
  types.addAll(Arrays.asList(annotation.value()));
  return replaceTargetAnnotation(targetAnnotationTree,types);
}
