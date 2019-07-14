private List<MethodMetadata> parseAndValidateMetadata(Contract contract,Class<?> targetType){
  List<MethodMetadata> methodMetadataList=Collections.emptyList();
  try {
    methodMetadataList=contract.parseAndValidatateMetadata(targetType);
  }
 catch (  Throwable ignored) {
  }
  return methodMetadataList;
}
