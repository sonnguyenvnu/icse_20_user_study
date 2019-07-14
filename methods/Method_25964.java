private static boolean hasAnyOfAnnotation(ExecutableElement input,List<String> annotations){
  return input.getAnnotationMirrors().stream().map(annotationMirror -> asType(annotationMirror.getAnnotationType().asElement())).anyMatch(type -> typeInAnnotations(type,annotations));
}
