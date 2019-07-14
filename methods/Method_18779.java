private static MethodSpec.Builder resTypeRegularBuilder(SpecModel specModel,PropModel prop,int requiredIndex,String name,List<ParameterSpec> parameters,String statement,Object... formatObjects){
  if (prop.hasVarArgs()) {
    final String propName=prop.getName();
    final String componentMemberInstanceName=getComponentMemberInstanceName(specModel);
    final ParameterizedTypeName varArgType=(ParameterizedTypeName)prop.getTypeName();
    final TypeName singleParameterType=varArgType.typeArguments.get(0);
    final ParameterizedTypeName listType=ParameterizedTypeName.get(ClassName.get(ArrayList.class),singleParameterType);
    CodeBlock.Builder codeBlockBuilder=CodeBlock.builder();
    if (prop.hasDefault(specModel.getPropDefaults())) {
      codeBlockBuilder.beginControlFlow("if (this.$L.$L == null || this.$L.$L == $L.$L)",componentMemberInstanceName,propName,componentMemberInstanceName,propName,specModel.getSpecName(),propName);
    }
 else {
      codeBlockBuilder.beginControlFlow("if (this.$L.$L == null)",componentMemberInstanceName,propName);
    }
    codeBlockBuilder.addStatement("this.$L.$L = new $T()",componentMemberInstanceName,propName,listType).endControlFlow().add("final $T res = ",singleParameterType.isBoxedPrimitive() ? singleParameterType.unbox() : singleParameterType).addStatement(statement,formatObjects).addStatement("this.$L.$L.add(res)",componentMemberInstanceName,propName);
    return getMethodSpecBuilder(specModel,prop,requiredIndex,name,parameters,codeBlockBuilder.build());
  }
  return getNoVarArgsMethodSpecBuilder(specModel,prop,requiredIndex,name,parameters,statement,formatObjects);
}
