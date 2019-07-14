private static MethodSpec.Builder resTypeListBuilder(SpecModel specModel,PropModel prop,int requiredIndex,String name,List<ParameterSpec> parameters,String statement,Object... formatObjects){
  final String propName=prop.getName();
  final String parameterName=parameters.get(0).name;
  final String componentMemberInstanceName=getComponentMemberInstanceName(specModel);
  final ParameterizedTypeName varArgType=(ParameterizedTypeName)prop.getTypeName();
  final TypeName resType=varArgType.typeArguments.get(0);
  final ParameterizedTypeName listType=ParameterizedTypeName.get(ClassName.get(ArrayList.class),resType);
  CodeBlock.Builder codeBlockBuilder=CodeBlock.builder().beginControlFlow("if ($L == null)",parameterName).addStatement("return this").endControlFlow();
  if (prop.hasDefault(specModel.getPropDefaults())) {
    codeBlockBuilder.beginControlFlow("if (this.$L.$L == null || this.$L.$L == $L.$L)",componentMemberInstanceName,propName,componentMemberInstanceName,propName,specModel.getSpecName(),propName);
  }
 else {
    codeBlockBuilder.beginControlFlow("if (this.$L.$L == null)",componentMemberInstanceName,propName);
  }
  CodeBlock codeBlock=codeBlockBuilder.addStatement("this.$L.$L = new $T()",componentMemberInstanceName,propName,listType).endControlFlow().beginControlFlow("for (int i = 0; i < $L.size(); i++)",parameterName).add("final $T res = ",resType.isBoxedPrimitive() ? resType.unbox() : resType).addStatement(statement,formatObjects).addStatement("this.$L.$L.add(res)",componentMemberInstanceName,propName).endControlFlow().build();
  return getMethodSpecBuilder(specModel,prop,requiredIndex,name,parameters,codeBlock);
}
