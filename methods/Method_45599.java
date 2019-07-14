/** 
 * Service property key format : sofa.consumer.service.{serivceName}.{configKey} For example : sofa.consumer.service.com.alipay.sofa.rpc.test.HelloService.timeout service name = com.alipay.sofa.rpc.test.HelloService config key   = timeout
 * @param serviceProKey service property key
 * @return extracted service name
 */
public static String extractServiceNameFromServiceProKey(String serviceProKey){
  if (!isServiceProKey(serviceProKey)) {
    return "";
  }
  return serviceProKey.substring(SERVICE_CONSUMER_PROPERTY_KEY_PREFIX.length() + 1,serviceProKey.lastIndexOf(KEY_SEPARATOR));
}
