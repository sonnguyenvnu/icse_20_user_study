private static MethodSpec getService(String implClass,String serviceInstanceName){
  return MethodSpec.methodBuilder("getService").addAnnotation(Override.class).addModifiers(Modifier.PROTECTED).returns(TypeName.OBJECT).addParameter(ParameterSpec.builder(SectionClassNames.SECTION,"section").build()).addStatement("return (($L) section).$L",implClass,serviceInstanceName).build();
}
