private static TypeSpecDataHolder resWithVarargsBuilders(SpecModel specModel,PropModel prop,int requiredIndex,ClassName annotationClassName,String resolver,TypeName varargsType,String varargsName){
  final TypeSpecDataHolder.Builder dataHolder=TypeSpecDataHolder.newBuilder();
  final boolean hasVarArgs=prop.hasVarArgs();
  final String name=hasVarArgs ? prop.getVarArgsSingleName() : prop.getName();
  dataHolder.addMethod(resTypeRegularBuilder(specModel,prop,requiredIndex,name + "Res",Arrays.asList(parameterWithoutNullableAnnotation(prop,TypeName.INT,"resId",annotation(annotationClassName)),ParameterSpec.builder(ArrayTypeName.of(varargsType),varargsName).build()),"$L.$L(resId, $L)",RESOURCE_RESOLVER,resolver + "Res",varargsName).varargs(true).build());
  if (hasVarArgs) {
    dataHolder.addMethod(resTypeListBuilder(specModel,prop,requiredIndex,prop.getName() + "Res",Arrays.asList(parameterWithoutNullableAnnotation(prop,ParameterizedTypeName.get(ClassNames.LIST,TypeName.INT.box()),"resIds",annotation(annotationClassName)),ParameterSpec.builder(ArrayTypeName.of(varargsType),varargsName).build()),"$L.$L(resIds.get(i), $L)",RESOURCE_RESOLVER,resolver + "Res",varargsName).varargs(true).build());
  }
  return dataHolder.build();
}
