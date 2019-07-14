/** 
 * New a proxy instance of  {@link DubboMetadataService} via the specified service name
 * @param serviceName the service name
 * @param version     the service version
 * @return a {@link DubboMetadataService} proxy
 */
protected DubboMetadataService newProxy(String serviceName,String version){
  return (DubboMetadataService)newProxyInstance(classLoader,new Class[]{DubboMetadataService.class},new DubboMetadataServiceInvocationHandler(serviceName,version,dubboGenericServiceFactory));
}
