/** 
 * Convert consumer to url.
 * @param consumerConfig the ConsumerConfig
 * @return the url list
 */
public static String convertConsumerToUrl(ConsumerConfig consumerConfig){
  StringBuilder sb=new StringBuilder(200);
  String host=SystemInfo.getLocalHost();
  sb.append(consumerConfig.getProtocol()).append("://").append(host).append("?version=1.0").append(getKeyPairs(RpcConstants.CONFIG_KEY_UNIQUEID,consumerConfig.getUniqueId())).append(getKeyPairs(RpcConstants.CONFIG_KEY_PID,RpcRuntimeContext.PID)).append(getKeyPairs(RpcConstants.CONFIG_KEY_TIMEOUT,consumerConfig.getTimeout())).append(getKeyPairs(RpcConstants.CONFIG_KEY_ID,consumerConfig.getId())).append(getKeyPairs(RpcConstants.CONFIG_KEY_GENERIC,consumerConfig.isGeneric())).append(getKeyPairs(RpcConstants.CONFIG_KEY_INTERFACE,consumerConfig.getInterfaceId())).append(getKeyPairs(RpcConstants.CONFIG_KEY_APP_NAME,consumerConfig.getAppName())).append(getKeyPairs(RpcConstants.CONFIG_KEY_SERIALIZATION,consumerConfig.getSerialization())).append(getKeyPairs(ProviderInfoAttrs.ATTR_START_TIME,RpcRuntimeContext.now())).append(convertMap2Pair(consumerConfig.getParameters()));
  addCommonAttrs(sb);
  return sb.toString();
}
