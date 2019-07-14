/** 
 * Method property key format : sofa.consumer.method.{serivceName}.{methodName}.{configKey} Example : sofa.consumer.method.com.alipay.sofa.rpc.test.HelloService.sayHello.timeout service name = com.alipay.sofa.rpc.test.HelloService method name  = sayHello config key   = timeout
 * @param methodProKey method property key
 * @return extracted method name
 */
public static String extractMethodNameFromMethodProKey(String methodProKey){
  if (!isMethodProKey(methodProKey)) {
    return "";
  }
  String serviceMethod=methodProKey.substring(METHOD_PROVIDER_PROPERTY_KEY_PREFIX.length() + 1,methodProKey.lastIndexOf(KEY_SEPARATOR));
  return serviceMethod.substring(serviceMethod.lastIndexOf(KEY_SEPARATOR) + 1);
}
