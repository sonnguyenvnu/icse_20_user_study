private static MethodSpec.Builder getNoVarArgsMethodSpecBuilder(SpecModel specModel,PropModel prop,int requiredIndex,String name,List<ParameterSpec> parameters,String statement,Object... formatObjects){
  CodeBlock codeBlock=CodeBlock.builder().add("this.$L.$L = ",getComponentMemberInstanceName(specModel),prop.getName()).addStatement(statement,formatObjects).build();
  return getMethodSpecBuilder(specModel,prop,requiredIndex,name,parameters,codeBlock);
}
