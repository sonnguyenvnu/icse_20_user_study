private static MethodSpec varArgBuilderBuilder(SpecModel specModel,PropModel prop,int requiredIndex){
  String varArgName=prop.getVarArgsSingleName();
  final ParameterizedTypeName varArgType=(ParameterizedTypeName)prop.getTypeName();
  final TypeName internalType=varArgType.typeArguments.get(0);
  CodeBlock codeBlock=CodeBlock.builder().beginControlFlow("if ($L == null)",varArgName + "Builder").addStatement("return this").endControlFlow().addStatement("$L($L.build())",varArgName,varArgName + "Builder").build();
  TypeName builderParameterType=ParameterizedTypeName.get(ClassNames.COMPONENT_BUILDER,getBuilderGenericTypes(internalType,ClassNames.COMPONENT_BUILDER));
  return getMethodSpecBuilder(specModel,prop,requiredIndex,varArgName,Arrays.asList(parameter(prop,builderParameterType,varArgName + "Builder")),codeBlock).build();
}
