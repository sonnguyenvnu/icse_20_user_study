private static TypeSpecDataHolder resBuilders(SpecModel specModel,PropModel prop,int requiredIndex,ClassName annotationClassName,String resolver){
  final TypeSpecDataHolder.Builder dataHolder=TypeSpecDataHolder.newBuilder();
  final boolean hasVarArgs=prop.hasVarArgs();
  final String name=hasVarArgs ? prop.getVarArgsSingleName() : prop.getName();
  final String typeCast=!prop.hasVarArgs() && prop.getTypeName().equals(ClassName.FLOAT.box()) ? "(float) " : "";
  dataHolder.addMethod(resTypeRegularBuilder(specModel,prop,requiredIndex,name + "Res",Arrays.asList(parameterWithoutNullableAnnotation(prop,TypeName.INT,"resId",annotation(annotationClassName))),"$L$L.$L(resId)",typeCast,RESOURCE_RESOLVER,resolver + "Res").build());
  if (hasVarArgs) {
    dataHolder.addMethod(resTypeListBuilder(specModel,prop,requiredIndex,prop.getName() + "Res",Arrays.asList(parameterWithoutNullableAnnotation(prop,ParameterizedTypeName.get(ClassNames.LIST,TypeName.INT.box()),"resIds")),"$L$L.$L(resIds.get(i))",typeCast,RESOURCE_RESOLVER,resolver + "Res").build());
  }
  return dataHolder.build();
}
