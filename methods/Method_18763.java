private static MethodSpec dynamicValueBuilder(SpecModel specModel,PropModel prop,int requiredIndex,TypeName dynamicValueType){
  return getMethodSpecBuilder(specModel,prop,requiredIndex,prop.getName(),Arrays.asList(parameter(prop,KotlinSpecUtils.getFieldTypeName(specModel,dynamicValueType),prop.getName())),prop.getName()).build();
}
