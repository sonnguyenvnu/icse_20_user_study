private Map<String,RestMethodMetadata> resolveRestRequestMetadataMap(Class<?> targetType){
  return contract.parseAndValidatateMetadata(targetType).stream().collect(Collectors.toMap(feign.MethodMetadata::configKey,this::restMethodMetadata));
}
