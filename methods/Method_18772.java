private static TypeSpecDataHolder pxBuilders(SpecModel specModel,PropModel prop,int requiredIndex){
  final TypeSpecDataHolder.Builder dataHolder=TypeSpecDataHolder.newBuilder();
  final boolean hasVarArgs=prop.hasVarArgs();
  final String name=hasVarArgs ? prop.getVarArgsSingleName() : prop.getName();
  dataHolder.addMethod(resTypeRegularBuilder(specModel,prop,requiredIndex,name + "Px",Arrays.asList(parameterWithoutNullableAnnotation(prop,hasVarArgs ? ((ParameterizedTypeName)prop.getTypeName()).typeArguments.get(0).unbox() : prop.getTypeName().unbox(),name,annotation(ClassNames.PX))),name).build());
  if (hasVarArgs) {
    dataHolder.addMethod(resTypeListBuilder(specModel,prop,requiredIndex,prop.getName() + "Px",Arrays.asList(parameterWithoutNullableAnnotation(prop,prop.getTypeName(),prop.getName())),prop.getName() + ".get(i)").build());
  }
  return dataHolder.build();
}
