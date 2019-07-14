static TypeSpecDataHolder generateGetDynamicProps(SpecModel specModel){
  final TypeSpecDataHolder.Builder typeSpecDataHolder=TypeSpecDataHolder.newBuilder();
  if (SpecModelUtils.getDynamicProps(specModel).isEmpty()) {
    return typeSpecDataHolder.build();
  }
  final MethodSpec methodSpec=MethodSpec.methodBuilder("getDynamicProps").addModifiers(Modifier.PROTECTED).addAnnotation(Override.class).returns(ArrayTypeName.of(ClassNames.DYNAMIC_VALUE)).addStatement("return $L",DYNAMIC_PROPS).build();
  return typeSpecDataHolder.addMethod(methodSpec).build();
}
