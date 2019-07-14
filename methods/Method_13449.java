private Map<Method,FeignMethodMetadata> getFeignMethodMetadataMap(String serviceName,Map<DubboTransportedMethodMetadata,RestMethodMetadata> feignRestMethodMetadataMap){
  Map<Method,FeignMethodMetadata> feignMethodMetadataMap=new HashMap<>();
  for (  Map.Entry<DubboTransportedMethodMetadata,RestMethodMetadata> entry : feignRestMethodMetadataMap.entrySet()) {
    RestMethodMetadata feignRestMethodMetadata=entry.getValue();
    RequestMetadata feignRequestMetadata=feignRestMethodMetadata.getRequest();
    DubboRestServiceMetadata metadata=repository.get(serviceName,feignRequestMetadata);
    if (metadata != null) {
      DubboTransportedMethodMetadata dubboTransportedMethodMetadata=entry.getKey();
      Map<String,Object> dubboTranslatedAttributes=dubboTransportedMethodMetadata.getAttributes();
      Method method=dubboTransportedMethodMetadata.getMethod();
      GenericService dubboGenericService=dubboGenericServiceFactory.create(metadata,dubboTranslatedAttributes);
      RestMethodMetadata dubboRestMethodMetadata=metadata.getRestMethodMetadata();
      MethodMetadata methodMetadata=dubboTransportedMethodMetadata.getMethodMetadata();
      FeignMethodMetadata feignMethodMetadata=new FeignMethodMetadata(dubboGenericService,dubboRestMethodMetadata,feignRestMethodMetadata);
      feignMethodMetadataMap.put(method,feignMethodMetadata);
    }
  }
  return feignMethodMetadataMap;
}
