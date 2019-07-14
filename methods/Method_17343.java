/** 
 * Unregisters the JMX management bean for the cache.
 * @param cache the cache to unregister
 * @param type the mxbean type
 */
public static void unregisterMXBean(CacheProxy<?,?> cache,MBeanType type){
  ObjectName objectName=getObjectName(cache,type);
  unregister(objectName);
}
