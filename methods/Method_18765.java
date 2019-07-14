private static MethodSpec regularBuilder(SpecModel specModel,PropModel prop,int requiredIndex,AnnotationSpec... extraAnnotations){
  return getMethodSpecBuilder(specModel,prop,requiredIndex,prop.getName(),Arrays.asList(parameter(prop,KotlinSpecUtils.getFieldTypeName(specModel,prop.getTypeName()),prop.getName(),extraAnnotations)),prop.getName()).build();
}
