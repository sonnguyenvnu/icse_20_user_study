private static String generatePropDefaultResInitializer(String resourceResolveMethodName,PropDefaultModel propDefaultModel,SpecModel specModel){
  StringBuilder builtInitializer=new StringBuilder();
  if (propDefaultModel.isResResolvable()) {
    return String.format(builtInitializer.append(RESOURCE_RESOLVER).append('.').append(resourceResolveMethodName).append('(').append(propDefaultModel.getResId()).append(')').toString(),specModel.getSpecName(),propDefaultModel.getName());
  }
  return builtInitializer.toString();
}
