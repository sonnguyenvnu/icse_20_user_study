/** 
 * Method property key format : sofa.consumer.method.{serivceName}.{methodName}.{configKey} Example : sofa.consumer.method.com.alipay.sofa.rpc.test.HelloService.sayHello.timeout service name = com.alipay.sofa.rpc.test.HelloService method name  = sayHello config key   = timeout
 * @param methodProKey method property key
 * @return extracted service name
 */
public static String extractServiceNameFromMethodProKey(String methodProKey){
  if (!isMethodProKey(methodProKey)) {
    return "";
  }
  String serviceMethod=methodProKey.substring(METHOD_PROVIDER_PROPERTY_KEY_PREFIX.length() + 1,methodProKey.lastIndexOf(KEY_SEPARATOR));
  return serviceMethod.substring(0,serviceMethod.lastIndexOf(KEY_SEPARATOR));
}
