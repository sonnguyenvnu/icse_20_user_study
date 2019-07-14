private static TypeSpecDataHolder attrBuilders(SpecModel specModel,PropModel prop,int requiredIndex,ClassName annotationClassName,String resolver){
  final TypeSpecDataHolder.Builder dataHolder=TypeSpecDataHolder.newBuilder();
  final boolean hasVarArgs=prop.hasVarArgs();
  final String name=hasVarArgs ? prop.getVarArgsSingleName() : prop.getName();
  final String typeCast=!prop.hasVarArgs() && prop.getTypeName().equals(ClassName.FLOAT.box()) ? "(float) " : "";
  dataHolder.addMethod(resTypeRegularBuilder(specModel,prop,requiredIndex,name + "Attr",Arrays.asList(parameterWithoutNullableAnnotation(prop,TypeName.INT,"attrResId",annotation(ClassNames.ATTR_RES)),parameterWithoutNullableAnnotation(prop,TypeName.INT,"defResId",annotation(annotationClassName))),"$L$L.$L(attrResId, defResId)",typeCast,RESOURCE_RESOLVER,resolver + "Attr").build());
  dataHolder.addMethod(resTypeRegularBuilder(specModel,prop,requiredIndex,name + "Attr",Arrays.asList(parameterWithoutNullableAnnotation(prop,TypeName.INT,"attrResId",annotation(ClassNames.ATTR_RES))),"$L$L.$L(attrResId, 0)",typeCast,RESOURCE_RESOLVER,resolver + "Attr").build());
  if (hasVarArgs) {
    dataHolder.addMethod(resTypeListBuilder(specModel,prop,requiredIndex,prop.getName() + "Attr",Arrays.asList(parameterWithoutNullableAnnotation(prop,ParameterizedTypeName.get(ClassNames.LIST,TypeName.INT.box()),"attrResIds"),parameterWithoutNullableAnnotation(prop,TypeName.INT,"defResId",annotation(annotationClassName))),"$L$L.$L(attrResIds.get(i), defResId)",typeCast,RESOURCE_RESOLVER,resolver + "Attr").build());
    dataHolder.addMethod(resTypeListBuilder(specModel,prop,requiredIndex,prop.getName() + "Attr",Arrays.asList(parameterWithoutNullableAnnotation(prop,ParameterizedTypeName.get(ClassNames.LIST,TypeName.INT.box()),"attrResIds")),"$L$L.$L(attrResIds.get(i), 0)",typeCast,RESOURCE_RESOLVER,resolver + "Attr").build());
  }
  return dataHolder.build();
}
