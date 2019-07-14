/** 
 * If the spec has injected dependencies, generate a DI constructor. Otherwise, generate a private constructor to enforce singleton-ity.
 */
static TypeSpecDataHolder generateConstructor(SpecModel specModel){
  final MethodSpec.Builder constructorBuilder=MethodSpec.constructorBuilder().addStatement("super($S)",specModel.getComponentName());
  if (specModel.hasInjectedDependencies()) {
    final MethodSpec diConstructor=specModel.getDependencyInjectionHelper().generateConstructor(specModel);
    constructorBuilder.addAnnotations(diConstructor.annotations).addCode(diConstructor.code).addModifiers(diConstructor.modifiers).addParameters(diConstructor.parameters);
  }
 else {
    constructorBuilder.addModifiers(Modifier.PRIVATE);
  }
  final boolean hasState=!specModel.getStateValues().isEmpty();
  if (hasState) {
    final ClassName stateContainerClass=ClassName.bestGuess(getStateContainerClassName(specModel));
    constructorBuilder.addStatement(STATE_CONTAINER_FIELD_NAME + " = new $T()",stateContainerClass);
  }
  return TypeSpecDataHolder.newBuilder().addMethod(constructorBuilder.build()).build();
}
