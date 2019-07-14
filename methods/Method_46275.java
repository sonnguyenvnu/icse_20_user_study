/** 
 * Check service exists
 * @param serviceName Service Name
 * @param methodName  Method name
 * @return if service and method exists, return true.
 */
public boolean checkService(String serviceName,String methodName){
  Invoker invoker=invokerMap.get(serviceName);
  return invoker instanceof ProviderProxyInvoker && getMethod(serviceName,methodName) != null;
}
