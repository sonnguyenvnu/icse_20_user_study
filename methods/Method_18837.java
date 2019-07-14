static TypeSpecDataHolder generateTransferState(SpecModel specModel){
  if (specModel.getStateValues().isEmpty()) {
    return TypeSpecDataHolder.newBuilder().build();
  }
  final String stateContainerClassNameWithTypeVars=getStateContainerClassNameWithTypeVars(specModel);
  MethodSpec.Builder methodSpec=MethodSpec.methodBuilder("transferState").addAnnotation(Override.class).addModifiers(Modifier.PROTECTED).addParameter(ParameterSpec.builder(specModel.getStateContainerClass(),"_prevStateContainer").build()).addParameter(ParameterSpec.builder(specModel.getStateContainerClass(),"_nextStateContainer").build()).addStatement("$L prevStateContainer = ($L) _prevStateContainer",stateContainerClassNameWithTypeVars,stateContainerClassNameWithTypeVars).addStatement("$L nextStateContainer = ($L) _nextStateContainer",stateContainerClassNameWithTypeVars,stateContainerClassNameWithTypeVars);
  for (  StateParamModel stateValue : specModel.getStateValues()) {
    methodSpec.addStatement("nextStateContainer.$L = prevStateContainer.$L",stateValue.getName(),stateValue.getName());
  }
  if (hasUpdateStateWithTransition(specModel)) {
    methodSpec.beginControlFlow("synchronized (prevStateContainer.$L)",GeneratorConstants.STATE_TRANSITIONS_FIELD_NAME).addStatement("nextStateContainer.$L = new ArrayList<>(prevStateContainer.$L)",GeneratorConstants.STATE_TRANSITIONS_FIELD_NAME,GeneratorConstants.STATE_TRANSITIONS_FIELD_NAME).endControlFlow();
  }
  return TypeSpecDataHolder.newBuilder().addMethod(methodSpec.build()).build();
}
