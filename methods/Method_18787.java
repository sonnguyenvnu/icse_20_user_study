private static MethodSpec generateBuildMethod(SpecModel specModel,int numRequiredProps){
  final MethodSpec.Builder buildMethodBuilder=MethodSpec.methodBuilder("build").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC).returns(specModel.getComponentTypeName());
  if (numRequiredProps > 0) {
    buildMethodBuilder.addStatement("checkArgs($L, $L, $L)",REQUIRED_PROPS_COUNT,"mRequired",REQUIRED_PROPS_NAMES);
  }
  final List<PropModel> dynamicProps=SpecModelUtils.getDynamicProps(specModel);
  if (!dynamicProps.isEmpty()) {
    final int count=dynamicProps.size();
    final String componentRef=getComponentMemberInstanceName(specModel);
    buildMethodBuilder.addStatement("$L.$L = new $T[$L]",componentRef,DYNAMIC_PROPS,ClassNames.DYNAMIC_VALUE,count);
    for (int i=0; i < count; i++) {
      buildMethodBuilder.addStatement("$L.$L[$L] = $L.$L",componentRef,DYNAMIC_PROPS,i,componentRef,dynamicProps.get(i).getName());
    }
  }
  return buildMethodBuilder.addStatement("return $L",getComponentMemberInstanceName(specModel)).build();
}
