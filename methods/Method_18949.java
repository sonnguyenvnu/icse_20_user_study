public static ImmutableList<AnnotationSpec> extractValidAnnotations(TypeElement element){
  final List<AnnotationSpec> annotations=new ArrayList<>();
  for (  AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
    if (isValidAnnotation(annotationMirror)) {
      annotations.add(AnnotationSpec.get(annotationMirror));
    }
  }
  return ImmutableList.copyOf(annotations);
}
