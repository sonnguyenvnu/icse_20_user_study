static TypeSpecDataHolder generateGetStateContainerWithLazyStateUpdatesApplied(SpecModel specModel){
  if (!SpecModelUtils.hasLazyState(specModel) || specModel.getEventMethods().size() == 0) {
    return TypeSpecDataHolder.newBuilder().build();
  }
  final String stateContainerClassName=getStateContainerClassName(specModel);
  MethodSpec methodSpec=MethodSpec.methodBuilder("getStateContainerWithLazyStateUpdatesApplied").addModifiers(Modifier.PRIVATE).returns(ClassName.bestGuess(stateContainerClassName)).addParameter(ParameterSpec.builder(specModel.getContextClass(),"c").build()).addParameter(ParameterSpec.builder(specModel.getComponentTypeName(),"component").build()).addStatement("$L $L = new $L()",stateContainerClassName,STATE_CONTAINER_NAME,stateContainerClassName).addStatement("transferState(component.$L, $L)",STATE_CONTAINER_FIELD_NAME,STATE_CONTAINER_NAME).addStatement("c.applyLazyStateUpdatesForContainer($L)",STATE_CONTAINER_NAME).addStatement("return $L",STATE_CONTAINER_NAME).build();
  return TypeSpecDataHolder.newBuilder().addMethod(methodSpec).build();
}
