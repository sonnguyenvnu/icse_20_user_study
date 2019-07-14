private static ParameterSpec parameterWithoutNullableAnnotation(PropModel prop,TypeName type,String name,AnnotationSpec... extraAnnotations){
  List<AnnotationSpec> externalAnnotations=new ArrayList<>();
  for (  AnnotationSpec annotationSpec : prop.getExternalAnnotations()) {
    if (!annotationSpec.type.toString().contains("Nullable")) {
      externalAnnotations.add(annotationSpec);
    }
  }
  return parameter(type,name,externalAnnotations,extraAnnotations);
}
