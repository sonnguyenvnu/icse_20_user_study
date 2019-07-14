private static TypeSpecDataHolder sipBuilders(SpecModel specModel,PropModel prop,int requiredIndex){
  final TypeSpecDataHolder.Builder dataHolder=TypeSpecDataHolder.newBuilder();
  final boolean hasVarArgs=prop.hasVarArgs();
  final String name=hasVarArgs ? prop.getVarArgsSingleName() : prop.getName();
  final String statement=!prop.hasVarArgs() && prop.getTypeName().equals(ClassName.FLOAT.box()) ? "(float) mResourceResolver.sipsToPixels(sip)" : "mResourceResolver.sipsToPixels(sip)";
  AnnotationSpec spAnnotation=AnnotationSpec.builder(ClassNames.DIMENSION).addMember("unit","$T.SP",ClassNames.DIMENSION).build();
  dataHolder.addMethod(resTypeRegularBuilder(specModel,prop,requiredIndex,name + "Sp",Arrays.asList(parameterWithoutNullableAnnotation(prop,TypeName.FLOAT,"sip",spAnnotation)),statement).build());
  if (hasVarArgs) {
    dataHolder.addMethod(resTypeListBuilder(specModel,prop,requiredIndex,prop.getName() + "Sp",Arrays.asList(parameterWithoutNullableAnnotation(prop,ParameterizedTypeName.get(ClassNames.LIST,TypeName.FLOAT.box()),"sips")),"mResourceResolver.sipsToPixels(sips.get(i))").build());
  }
  return dataHolder.build();
}
