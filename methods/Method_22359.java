private void createFactoryClass() throws IOException {
  final TypeName configurationBuilderFactory=Types.CONFIGURATION_BUILDER_FACTORY;
  Strings.writeClass(processingEnv.getFiler(),TypeSpec.classBuilder(factoryName).addModifiers(Modifier.PUBLIC).addSuperinterface(configurationBuilderFactory).addAnnotation(AnnotationSpec.builder(AutoService.class).addMember("value","$T.class",configurationBuilderFactory).build()).addMethod(Types.overriding(Types.getOnlyMethod(processingEnv,Strings.CONFIGURATION_BUILDER_FACTORY)).addAnnotation(Types.NON_NULL).addStatement("return new $T($L)",ClassName.get(PACKAGE,builderName),PARAM_0).build()).build());
}
