/** 
 * Consumer method property key format : sofa.consumer.service.{serviceName}.{methodName}.{configKey}
 */
public static String buildConsumerMethodProKey(String serviceName,String methodName,String proKey){
  return METHOD_CONSUMER_PROPERTY_KEY_PREFIX + KEY_SEPARATOR + serviceName + KEY_SEPARATOR + methodName + KEY_SEPARATOR + proKey;
}
