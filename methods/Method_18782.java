private static MethodSpec.Builder getMethodSpecBuilder(SpecModel specModel,PropModel prop,int requiredIndex,String name,List<ParameterSpec> parameters,CodeBlock codeBlock){
  final MethodSpec.Builder builder=MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC).returns(getBuilderType(specModel));
  if (prop.isCommonProp()) {
    builder.addAnnotation(Override.class);
    if (!prop.overrideCommonPropBehavior()) {
      final CodeBlock.Builder superCodeBlock=CodeBlock.builder().add("super.$L(",name);
      boolean isFirstParam=true;
      for (      ParameterSpec param : parameters) {
        if (!isFirstParam) {
          superCodeBlock.add(", ");
        }
        superCodeBlock.add("$L",param.name);
        isFirstParam=false;
      }
      builder.addCode(superCodeBlock.add(");\n").build());
    }
  }
  for (  ParameterSpec param : parameters) {
    builder.addParameter(param);
  }
  builder.addCode(codeBlock);
  if (!prop.isOptional()) {
    builder.addStatement("$L.set($L)","mRequired",requiredIndex);
  }
  builder.addStatement("return this");
  return builder;
}
