public static CodeBlock getDelegationCode(SpecModel specModel,SpecMethodModel<DelegateMethod,Void> delegateMethod,DelegateMethodDescription methodDescription,EnumSet<RunMode> runMode){
  final CodeBlock.Builder acquireStatements=CodeBlock.builder();
  final CodeBlock.Builder releaseStatements=CodeBlock.builder();
  final List<ParamTypeAndName> delegationParams=new ArrayList<>(delegateMethod.methodParams.size());
  for (int i=0, size=delegateMethod.methodParams.size(); i < size; i++) {
    final MethodParamModel methodParamModel=delegateMethod.methodParams.get(i);
    final int definedParameterTypesSize=methodDescription.definedParameterTypes.size();
    if (i < definedParameterTypesSize) {
      delegationParams.add(ParamTypeAndName.create(methodParamModel.getTypeName(),methodParamModel.getName()));
    }
 else     if (i < definedParameterTypesSize + methodDescription.optionalParameters.size() && shouldIncludeOptionalParameter(methodParamModel,methodDescription.optionalParameters.get(i - definedParameterTypesSize))) {
      final MethodParamModel extraDefinedParam=methodDescription.optionalParameters.get(i - definedParameterTypesSize);
      delegationParams.add(ParamTypeAndName.create(extraDefinedParam.getTypeName(),extraDefinedParam.getName()));
    }
 else     if (methodParamModel instanceof DiffPropModel || methodParamModel instanceof DiffStateParamModel) {
      acquireStatements.addStatement("$T $L = new $T(_prevImpl == null ? null : _prevImpl.$L, " + "_nextImpl == null ? null : _nextImpl.$L)",methodParamModel.getTypeName(),methodParamModel.getName(),methodParamModel.getTypeName(),ComponentBodyGenerator.getImplAccessor(specModel,methodParamModel),ComponentBodyGenerator.getImplAccessor(specModel,methodParamModel));
      delegationParams.add(ParamTypeAndName.create(methodParamModel.getTypeName(),methodParamModel.getName()));
    }
 else     if (isOutputType(methodParamModel.getTypeName())) {
      String localOutputName=methodParamModel.getName() + "Tmp";
      acquireStatements.add("$T $L = new Output<>();\n",methodParamModel.getTypeName(),localOutputName);
      delegationParams.add(ParamTypeAndName.create(methodParamModel.getTypeName(),localOutputName));
      final boolean isPropOutput=SpecModelUtils.isPropOutput(specModel,methodParamModel);
      if (isPropOutput) {
        releaseStatements.beginControlFlow("if ($L.get() != null)",localOutputName);
      }
      releaseStatements.addStatement("$L = $L.get()",getImplAccessor(specModel,methodParamModel),localOutputName);
      if (isPropOutput) {
        releaseStatements.endControlFlow();
      }
    }
 else     if (isStateValueType(methodParamModel.getTypeName())) {
      acquireStatements.add("$T $L = new StateValue<>();\n",methodParamModel.getTypeName(),methodParamModel.getName());
      delegationParams.add(ParamTypeAndName.create(methodParamModel.getTypeName(),methodParamModel.getName()));
      if (delegateMethod.name.toString().equals("createInitialState")) {
        releaseStatements.beginControlFlow("if ($L.get() != null)",methodParamModel.getName());
      }
      releaseStatements.addStatement("$L = $L.get()",getImplAccessor(specModel,methodParamModel),methodParamModel.getName());
      if (delegateMethod.name.toString().equals("createInitialState")) {
        releaseStatements.endControlFlow();
      }
    }
 else     if (methodParamModel instanceof RenderDataDiffModel) {
      final String diffName="_" + methodParamModel.getName() + "Diff";
      CodeBlock block=CodeBlock.builder().add("$T $L = new $T(\n",methodParamModel.getTypeName(),diffName,methodParamModel.getTypeName()).indent().add("$L == null ? null : $L.$L,\n",PREVIOUS_RENDER_DATA_FIELD_NAME,PREVIOUS_RENDER_DATA_FIELD_NAME,methodParamModel.getName()).add("$L);\n",getImplAccessor(specModel,methodParamModel)).unindent().build();
      acquireStatements.add(block);
      delegationParams.add(ParamTypeAndName.create(methodParamModel.getTypeName(),diffName));
    }
 else {
      delegationParams.add(ParamTypeAndName.create(methodParamModel.getTypeName(),getImplAccessor(specModel,methodParamModel)));
    }
  }
  final CodeBlock.Builder codeBlock=CodeBlock.builder().add(acquireStatements.build());
  final CodeBlock directDelegation=getDelegationMethod(specModel,delegateMethod.name,methodDescription.returnType,ImmutableList.copyOf(delegationParams));
  if (runMode.contains(RunMode.HOTSWAP)) {
    codeBlock.add(HotswapGenerator.generateDelegatingMethod(specModel,delegateMethod.name.toString(),delegateMethod.returnType,ImmutableList.copyOf(delegationParams),directDelegation));
  }
 else {
    codeBlock.add(directDelegation);
  }
  return codeBlock.add(releaseStatements.build()).build();
}
