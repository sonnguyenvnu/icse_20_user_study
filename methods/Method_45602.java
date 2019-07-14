/** 
 * Consumer service property key format : sofa.consumer.service.{serviceName}.{configKey}
 */
public static String buildConsumerServiceProKey(String serviceName,String proKey){
  return SERVICE_CONSUMER_PROPERTY_KEY_PREFIX + KEY_SEPARATOR + serviceName + KEY_SEPARATOR + proKey;
}
