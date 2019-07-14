@Override public Set<RestMethodMetadata> resolveMethodRestMetadata(Class<?> targetType){
  List<Method> feignContractMethods=selectFeignContractMethods(targetType);
  return contracts.stream().map(contract -> parseAndValidateMetadata(contract,targetType)).flatMap(v -> v.stream()).map(methodMetadata -> resolveMethodRestMetadata(methodMetadata,targetType,feignContractMethods)).collect(Collectors.toSet());
}
