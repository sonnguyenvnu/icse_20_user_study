protected static List<ParameterSpec> buildParamSpecs(List<? extends VariableElement> params){
  List<ParameterSpec> result=new ArrayList<>();
  for (  VariableElement param : params) {
    Builder builder=ParameterSpec.builder(TypeName.get(param.asType()),param.getSimpleName().toString());
    for (    AnnotationMirror annotation : param.getAnnotationMirrors()) {
      builder.addAnnotation(AnnotationSpec.get(annotation));
    }
    result.add(builder.build());
  }
  return result;
}
