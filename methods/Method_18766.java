private static MethodSpec varArgBuilder(SpecModel specModel,PropModel prop,int requiredIndex,AnnotationSpec... extraAnnotations){
  String varArgName=prop.getVarArgsSingleName();
  final String propName=prop.getName();
  final String implMemberInstanceName=getComponentMemberInstanceName(specModel);
  final ParameterizedTypeName varArgType=(ParameterizedTypeName)prop.getTypeName();
  final TypeName varArgTypeArgumentTypeName=varArgType.typeArguments.get(0);
  final TypeName varArgTypeName=getParameterTypeName(specModel,varArgTypeArgumentTypeName);
  final ParameterizedTypeName listType=ParameterizedTypeName.get(ClassName.get(ArrayList.class),varArgTypeName);
  CodeBlock.Builder codeBlockBuilder=CodeBlock.builder().beginControlFlow("if ($L == null)",varArgName).addStatement("return this").endControlFlow();
  if (prop.hasDefault(specModel.getPropDefaults())) {
    codeBlockBuilder.beginControlFlow("if (this.$L.$L == null || this.$L.$L == $L.$L)",implMemberInstanceName,propName,implMemberInstanceName,propName,specModel.getSpecName(),propName);
  }
 else {
    codeBlockBuilder.beginControlFlow("if (this.$L.$L == null)",implMemberInstanceName,propName);
  }
  CodeBlock codeBlock=codeBlockBuilder.addStatement("this.$L.$L = new $T()",implMemberInstanceName,propName,listType).endControlFlow().addStatement("this.$L.$L.add($L)",implMemberInstanceName,propName,varArgName).build();
  return getMethodSpecBuilder(specModel,prop,requiredIndex,varArgName,Arrays.asList(parameter(prop,varArgTypeName,varArgName,extraAnnotations)),codeBlock).build();
}
