static CodeBlock getCompareStatement(SpecModel specModel,String implInstanceName,MethodParamModel field){
  final String implAccessor=getImplAccessor(specModel,field,true);
  return getCompareStatement(specModel,field,implAccessor,implInstanceName + "." + implAccessor);
}
