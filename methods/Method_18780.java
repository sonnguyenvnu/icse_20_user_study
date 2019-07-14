private static MethodSpec.Builder getMethodSpecBuilder(SpecModel specModel,PropModel prop,int requiredIndex,String name,List<ParameterSpec> parameters,String statement,Object... formatObjects){
  if (prop.hasVarArgs()) {
    final String propName=prop.getName();
    final String componentMemberInstanceName=getComponentMemberInstanceName(specModel);
    CodeBlock.Builder codeBlockBuilder=CodeBlock.builder().beginControlFlow("if ($L == null)",propName).addStatement("return this").endControlFlow();
    if (prop.hasDefault(specModel.getPropDefaults())) {
      codeBlockBuilder.beginControlFlow("if (this.$L.$L == null || this.$L.$L.isEmpty() || this.$L.$L == $L.$L)",componentMemberInstanceName,propName,componentMemberInstanceName,propName,componentMemberInstanceName,propName,specModel.getSpecName(),propName);
    }
 else {
      codeBlockBuilder.beginControlFlow("if (this.$L.$L == null || this.$L.$L.isEmpty())",componentMemberInstanceName,propName,componentMemberInstanceName,propName);
    }
    codeBlockBuilder.addStatement("this.$L.$L = $L",componentMemberInstanceName,propName,propName).nextControlFlow("else").addStatement("this.$L.$L.addAll($L)",componentMemberInstanceName,propName,propName).endControlFlow();
    return getMethodSpecBuilder(specModel,prop,requiredIndex,name,parameters,codeBlockBuilder.build());
  }
  return getNoVarArgsMethodSpecBuilder(specModel,prop,requiredIndex,name,parameters,statement,formatObjects);
}
