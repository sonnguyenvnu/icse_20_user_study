private static MethodSpec generateStaticRegisterMethod(ClassName contextClassName,SpecMethodModel<EventMethod,WorkingRangeDeclarationModel> methodModel){
  String nameInAnnotation=(methodModel.typeModel != null) ? methodModel.typeModel.name : "";
  if (nameInAnnotation == null || nameInAnnotation.length() == 0) {
    throw new RuntimeException("Must provide a name for @OnEnteredRange and @OnExitedRange.");
  }
  final String methodName="register" + nameInAnnotation.substring(0,1).toUpperCase(Locale.US) + nameInAnnotation.substring(1) + "WorkingRange";
  MethodSpec.Builder registerMethod=MethodSpec.methodBuilder(methodName).addModifiers(Modifier.STATIC);
  registerMethod.addParameter(contextClassName,"c").addParameter(ClassNames.WORKING_RANGE,"workingRange").beginControlFlow("if (workingRange == null)").addStatement("return").endControlFlow().addStatement("$T component = c.getComponentScope()",ClassNames.COMPONENT).addStatement("registerWorkingRange(\"$L\", workingRange, component)",nameInAnnotation);
  return registerMethod.build();
}
