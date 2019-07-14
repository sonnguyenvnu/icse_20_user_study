/** 
 * Returns the annotation on  {@code classTree} whose type's FQCN is {@code annotationName}. 
 */
static Optional<AnnotationTree> findAnnotation(String annotationName,ClassTree classTree){
  for (  AnnotationTree annotationTree : classTree.getModifiers().getAnnotations()) {
    ClassSymbol annotationClass=(ClassSymbol)getSymbol(annotationTree.getAnnotationType());
    if (annotationClass.fullname.contentEquals(annotationName)) {
      return Optional.of(annotationTree);
    }
  }
  return Optional.absent();
}
