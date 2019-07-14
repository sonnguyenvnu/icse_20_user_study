private static List<AnnotationSpec> getExternalAnnotations(VariableElement param){
  final List<? extends AnnotationMirror> annotationMirrors=param.getAnnotationMirrors();
  final List<AnnotationSpec> annotations=new ArrayList<>();
  for (  AnnotationMirror annotationMirror : annotationMirrors) {
    if (annotationMirror.getAnnotationType().toString().startsWith(COMPONENTS_PACKAGE)) {
      continue;
    }
    final AnnotationSpec.Builder annotationSpec=AnnotationSpec.builder(ClassName.bestGuess(annotationMirror.getAnnotationType().toString()));
    Map<? extends ExecutableElement,? extends AnnotationValue> elementValues=annotationMirror.getElementValues();
    for (    Map.Entry<? extends ExecutableElement,? extends AnnotationValue> elementValue : elementValues.entrySet()) {
      annotationSpec.addMember(elementValue.getKey().getSimpleName().toString(),elementValue.getValue().toString());
    }
    annotations.add(annotationSpec.build());
  }
  return annotations;
}
