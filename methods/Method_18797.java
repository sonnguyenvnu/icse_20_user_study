static MethodSpec generateStateContainerGetter(TypeName stateContainerClassName){
  return MethodSpec.methodBuilder("getStateContainer").addModifiers(Modifier.PROTECTED).addAnnotation(Override.class).returns(stateContainerClassName).addStatement("return $N",STATE_CONTAINER_FIELD_NAME).build();
}
