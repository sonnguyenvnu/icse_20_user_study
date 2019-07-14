private static MethodSpec builderBuilder(SpecModel specModel,PropModel prop,int requiredIndex,ClassName builderClass,boolean hasGeneric){
  return getMethodSpecBuilder(specModel,prop,requiredIndex,prop.getName(),Arrays.asList(parameter(prop,hasGeneric ? ParameterizedTypeName.get(builderClass,getBuilderGenericTypes(prop,builderClass)) : builderClass,prop.getName() + "Builder")),"$L == null ? null : $L.build()",prop.getName() + "Builder",prop.getName() + "Builder").build();
}
