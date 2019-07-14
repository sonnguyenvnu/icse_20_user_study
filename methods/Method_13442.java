public Map<DubboTransportedMethodMetadata,RestMethodMetadata> resolve(Class<?> targetType){
  Set<DubboTransportedMethodMetadata> dubboTransportedMethodMetadataSet=resolveDubboTransportedMethodMetadataSet(targetType);
  Map<String,RestMethodMetadata> restMethodMetadataMap=resolveRestRequestMetadataMap(targetType);
  return dubboTransportedMethodMetadataSet.stream().collect(Collectors.toMap(methodMetadata -> methodMetadata,methodMetadata -> {
    RestMethodMetadata restMethodMetadata=restMethodMetadataMap.get(configKey(targetType,methodMetadata.getMethod()));
    restMethodMetadata.setMethod(methodMetadata.getMethodMetadata());
    return restMethodMetadata;
  }
));
}
