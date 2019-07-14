private static TypeSpecDataHolder dipBuilders(SpecModel specModel,PropModel prop,int requiredIndex){
  final TypeSpecDataHolder.Builder dataHolder=TypeSpecDataHolder.newBuilder();
  final boolean hasVarArgs=prop.hasVarArgs();
  final String name=hasVarArgs ? prop.getVarArgsSingleName() : prop.getName();
  final String statement=!prop.hasVarArgs() && prop.getTypeName().equals(ClassName.FLOAT.box()) ? "(float) mResourceResolver.dipsToPixels(dip)" : "mResourceResolver.dipsToPixels(dip)";
  AnnotationSpec dipAnnotation=AnnotationSpec.builder(ClassNames.DIMENSION).addMember("unit","$T.DP",ClassNames.DIMENSION).build();
  dataHolder.addMethod(resTypeRegularBuilder(specModel,prop,requiredIndex,name + "Dip",Arrays.asList(parameterWithoutNullableAnnotation(prop,TypeName.FLOAT,"dip",dipAnnotation)),statement).build());
  if (hasVarArgs) {
    dataHolder.addMethod(resTypeListBuilder(specModel,prop,requiredIndex,prop.getName() + "Dip",Arrays.asList(parameterWithoutNullableAnnotation(prop,ParameterizedTypeName.get(ClassNames.LIST,TypeName.FLOAT.box()),"dips")),"mResourceResolver.dipsToPixels(dips.get(i))").build());
  }
  return dataHolder.build();
}
