protected RestMethodMetadata resolveMethodRestMetadata(MethodMetadata methodMetadata,Class<?> targetType,List<Method> feignContractMethods){
  String configKey=methodMetadata.configKey();
  Method feignContractMethod=getMatchedFeignContractMethod(targetType,feignContractMethods,configKey);
  RestMethodMetadata metadata=new RestMethodMetadata(methodMetadata);
  metadata.setMethod(new org.springframework.cloud.alibaba.dubbo.metadata.MethodMetadata(feignContractMethod));
  return metadata;
}
