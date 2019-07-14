private void createConfigClass(@NonNull List<Element> elements) throws IOException {
  final TypeSpec.Builder classBuilder=TypeSpec.classBuilder(configName).addSuperinterface(Serializable.class).addSuperinterface(org.acra.config.Configuration.class).addModifiers(Modifier.PUBLIC,Modifier.FINAL);
  Strings.addClassJavadoc(classBuilder,ClassName.get(baseAnnotation.asType()));
  final MethodSpec.Builder constructor=MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC).addParameter(ParameterSpec.builder(ClassName.get(PACKAGE,builderName),PARAM_0).addAnnotation(Types.NON_NULL).build());
  elements.stream().filter(ConfigElement.class::isInstance).map(ConfigElement.class::cast).forEach(element -> element.addToConfig(classBuilder,constructor));
  classBuilder.addMethod(constructor.build());
  Strings.writeClass(processingEnv.getFiler(),classBuilder.build());
}
