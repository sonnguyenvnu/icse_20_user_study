/** 
 * Reads annotation value from annotated element. If annotation does not exist, returns  {@code null}.
 */
public ActionAnnotationValues readAnnotationValue(final AnnotatedElement annotatedElement){
  for (  final AnnotationParser annotationParser : annotationParsers) {
    if (annotationParser.hasAnnotationOn(annotatedElement)) {
      return ActionAnnotationValues.of(annotationParser,annotatedElement);
    }
  }
  return null;
}
