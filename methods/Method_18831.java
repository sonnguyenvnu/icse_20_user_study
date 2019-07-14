public static TypeSpecDataHolder generateCallsShouldUpdateOnMount(MountSpecModel specModel){
  final TypeSpecDataHolder.Builder dataHolder=TypeSpecDataHolder.newBuilder();
  final ShouldUpdate shouldUpdateAnnotation=getShouldUpdateAnnotation(specModel);
  if (shouldUpdateAnnotation != null && shouldUpdateAnnotation.onMount()) {
    dataHolder.addMethod(MethodSpec.methodBuilder("callsShouldUpdateOnMount").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC).returns(TypeName.BOOLEAN).addStatement("return true").build());
  }
  return dataHolder.build();
}
