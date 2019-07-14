static TypeSpecDataHolder generateHasState(SpecModel specModel){
  final TypeSpecDataHolder.Builder typeSpecDataHolder=TypeSpecDataHolder.newBuilder();
  if (specModel.shouldGenerateHasState() && !specModel.getStateValues().isEmpty()) {
    typeSpecDataHolder.addMethod(MethodSpec.methodBuilder("hasState").addAnnotation(Override.class).addModifiers(Modifier.PROTECTED).returns(TypeName.BOOLEAN).addStatement("return true").build());
  }
  return typeSpecDataHolder.build();
}
