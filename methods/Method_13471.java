/** 
 * Get a proxy instance of  {@link DubboMetadataService} via the specified service name
 * @param serviceName the service name
 * @return a {@link DubboMetadataService} proxy
 */
public DubboMetadataService getProxy(String serviceName){
  return dubboMetadataServiceCache.get(serviceName);
}
