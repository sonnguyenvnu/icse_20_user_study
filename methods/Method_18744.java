private static List<AnnotationSpec> getExternalAnnotations(PsiParameter param){
  PsiAnnotation[] annotationsOnParam=AnnotationUtil.getAllAnnotations(param,false,null);
  final List<AnnotationSpec> annotations=new ArrayList<>();
  for (  PsiAnnotation annotationOnParam : annotationsOnParam) {
    if (annotationOnParam.getQualifiedName().startsWith(COMPONENTS_PACKAGE)) {
      continue;
    }
    final AnnotationSpec.Builder annotationSpec=AnnotationSpec.builder(ClassName.bestGuess(annotationOnParam.getQualifiedName()));
    PsiNameValuePair[] paramAttributes=annotationOnParam.getParameterList().getAttributes();
    for (    PsiNameValuePair attribute : paramAttributes) {
      annotationSpec.addMember(attribute.getName(),attribute.getLiteralValue());
    }
    annotations.add(annotationSpec.build());
  }
  return annotations;
}
