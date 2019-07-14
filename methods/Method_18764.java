private static MethodSpec dynamicValueSimpleBuilder(SpecModel specModel,PropModel prop,int requiredIndex,TypeName dynamicValueType){
  return getMethodSpecBuilder(specModel,prop,requiredIndex,prop.getName(),Arrays.asList(parameter(prop,KotlinSpecUtils.getFieldTypeName(specModel,prop.getTypeName()),prop.getName())),"new $T($L)",dynamicValueType,prop.getName()).build();
}
