public GenericService create(String serviceName,Class<?> serviceClass,String version){
  String interfaceName=serviceClass.getName();
  ReferenceBean<GenericService> referenceBean=build(interfaceName,version,serviceName,emptyMap());
  return referenceBean.get();
}
