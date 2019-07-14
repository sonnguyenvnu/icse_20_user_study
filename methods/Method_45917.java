/** 
 * Fetch result class for cache according  service and method
 * @param service    interface name
 * @param methodName method name
 * @return response class
 */
public Class getResClass(String service,String methodName){
  String key=service + "#" + methodName;
  Class reqClass=responseClassCache.get(key);
  if (reqClass == null) {
    String interfaceClass=ConfigUniqueNameGenerator.getInterfaceName(service);
    Class clazz=ClassUtils.forName(interfaceClass,true);
    loadClassToCache(key,clazz,methodName);
  }
  return responseClassCache.get(key);
}
