/** 
 * Initializes  {@link DubboMetadataService}'s Proxy
 * @param serviceName the service name
 * @param version     the service version
 * @return a {@link DubboMetadataService} proxy
 */
public DubboMetadataService initProxy(String serviceName,String version){
  return dubboMetadataServiceCache.computeIfAbsent(serviceName,name -> newProxy(name,version));
}
