private static MethodSpec createService(String serviceInstanceName){
  return MethodSpec.methodBuilder("createService").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC).addParameter(ParameterSpec.builder(SectionClassNames.SECTION_CONTEXT,"context").build()).addStatement("$L = onCreateService(context)",serviceInstanceName).build();
}
