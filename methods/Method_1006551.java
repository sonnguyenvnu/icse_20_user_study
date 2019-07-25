/** 
 * Returns the completion candidates from the annotations present on the specified element.
 * @param element the method or field annotated with {@code @Option} or {@code @Parameters}
 * @return the completion candidates or {@code null} if not found
 */
public static Iterable<String> extract(Element element){
  List<? extends AnnotationMirror> annotationMirrors=element.getAnnotationMirrors();
  for (  AnnotationMirror mirror : annotationMirrors) {
    DeclaredType annotationType=mirror.getAnnotationType();
    if (TypeUtil.isOption(annotationType) || TypeUtil.isParameter(annotationType)) {
      Map<? extends ExecutableElement,? extends AnnotationValue> elementValues=mirror.getElementValues();
      for (      ExecutableElement attribute : elementValues.keySet()) {
        if ("completionCandidates".equals(attribute.getSimpleName().toString())) {
          AnnotationValue typeMirror=elementValues.get(attribute);
          return new CompletionCandidatesMetaData((TypeMirror)typeMirror);
        }
      }
    }
  }
  return null;
}
