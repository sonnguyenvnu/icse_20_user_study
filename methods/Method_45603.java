/** 
 * Provider service property key format : sofa.provider.service.{serviceName}.{configKey}
 */
public static String buildProviderServiceProKey(String serviceName,String proKey){
  return SERVICE_PROVIDER_PROPERTY_KEY_PREFIX + KEY_SEPARATOR + serviceName + KEY_SEPARATOR + proKey;
}
