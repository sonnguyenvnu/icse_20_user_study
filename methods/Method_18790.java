private static MethodSpec createGetterMethod(SpecModel specModel,SpecMethodModel<DelegateMethod,Void> onCalculateCachedValueMethod,String cachedValueName){
  final TypeName cachedValueType=onCalculateCachedValueMethod.returnType;
  MethodSpec.Builder methodSpec=MethodSpec.methodBuilder(getCachedValueGetterName(cachedValueName)).addModifiers(Modifier.PRIVATE).returns(cachedValueType).addStatement("$T c = getScopedContext()",specModel.getContextClass());
  final CodeBlock.Builder codeBlock=CodeBlock.builder();
  codeBlock.add("final $L inputs = new $L(",getInputsClassName(cachedValueName),getInputsClassName(cachedValueName)).indent();
  final int paramSize=onCalculateCachedValueMethod.methodParams.size();
  if (paramSize == 0) {
    codeBlock.add(");\n");
  }
 else {
    for (int i=0; i < paramSize; i++) {
      if (i < paramSize - 1) {
        codeBlock.add("$L,",ComponentBodyGenerator.getImplAccessor(specModel,onCalculateCachedValueMethod.methodParams.get(i)));
      }
 else {
        codeBlock.add("$L);\n",ComponentBodyGenerator.getImplAccessor(specModel,onCalculateCachedValueMethod.methodParams.get(i)));
      }
    }
  }
  codeBlock.unindent();
  methodSpec.addCode(codeBlock.build()).addStatement("$T $L = ($T) c.getCachedValue(inputs)",cachedValueType.box(),cachedValueName,cachedValueType.box()).beginControlFlow("if ($L == null)",cachedValueName);
  final CodeBlock.Builder delegation=CodeBlock.builder();
  delegation.add("$L = $L.$L(",cachedValueName,specModel.getSpecName(),onCalculateCachedValueMethod.name).indent();
  if (paramSize == 0) {
    delegation.add(");\n");
  }
 else {
    for (int i=0; i < paramSize; i++) {
      if (i < paramSize - 1) {
        delegation.add("$L,",ComponentBodyGenerator.getImplAccessor(specModel,onCalculateCachedValueMethod.methodParams.get(i)));
      }
 else {
        delegation.add("$L);\n",ComponentBodyGenerator.getImplAccessor(specModel,onCalculateCachedValueMethod.methodParams.get(i)));
      }
    }
  }
  methodSpec.addCode(delegation.unindent().build()).addStatement("c.putCachedValue(inputs, $L)",cachedValueName).endControlFlow().addStatement("return $L",cachedValueName);
  return methodSpec.build();
}
