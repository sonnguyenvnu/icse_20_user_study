private static MethodSpec componentBuilder(SpecModel specModel,PropModel prop,int requiredIndex){
  return getMethodSpecBuilder(specModel,prop,requiredIndex,prop.getName(),Arrays.asList(parameter(prop,prop.getTypeName(),prop.getName())),"$L == null ? null : $L.makeShallowCopy()",prop.getName(),prop.getName()).build();
}
