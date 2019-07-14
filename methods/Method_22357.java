private void createBuilderClass(@NonNull List<Element> elements) throws IOException {
  final TypeSpec.Builder classBuilder=TypeSpec.classBuilder(builderName).addModifiers(Modifier.FINAL);
  final TypeName baseAnnotation=TypeName.get(this.baseAnnotation.asType());
  Strings.addClassJavadoc(classBuilder,baseAnnotation);
  final MethodSpec.Builder constructor=MethodSpec.constructorBuilder().addParameter(ParameterSpec.builder(Types.CONTEXT,PARAM_0).addAnnotation(Types.NON_NULL).build()).addJavadoc("@param $L object annotated with {@link $T}\n",PARAM_0,baseAnnotation).addStatement("final $1T $2L = $3L.getClass().getAnnotation($1T.class)",baseAnnotation,VAR_ANNOTATION,PARAM_0);
  if (!configuration.isPlugin()) {
    classBuilder.addModifiers(Modifier.PUBLIC).addSuperinterface(ConfigurationBuilder.class);
    constructor.addModifiers(Modifier.PUBLIC);
  }
 else {
    classBuilder.addSuperinterface(ClassName.get(PACKAGE,builderVisibleName));
  }
  final CodeBlock.Builder always=CodeBlock.builder();
  final CodeBlock.Builder whenAnnotationPresent=CodeBlock.builder();
  final CodeBlock.Builder whenAnnotationMissing=CodeBlock.builder();
  ClassName builder=ClassName.get(PACKAGE,builderName);
  elements.stream().filter(BuilderElement.class::isInstance).map(BuilderElement.class::cast).forEach(m -> m.addToBuilder(classBuilder,builder,always,whenAnnotationPresent,whenAnnotationMissing));
  constructor.addCode(always.build()).beginControlFlow("if ($L)",Strings.FIELD_ENABLED).addCode(whenAnnotationPresent.build()).nextControlFlow("else").addCode(whenAnnotationMissing.build()).endControlFlow();
  classBuilder.addMethod(constructor.build());
  final BuildMethodCreator build=new BuildMethodCreator(Types.getOnlyMethod(processingEnv,ConfigurationBuilder.class.getName()),ClassName.get(PACKAGE,configName));
  elements.stream().filter(ValidatedElement.class::isInstance).map(ValidatedElement.class::cast).forEach(element -> element.addToBuildMethod(build));
  classBuilder.addMethod(build.build());
  Strings.writeClass(processingEnv.getFiler(),classBuilder.build());
}
