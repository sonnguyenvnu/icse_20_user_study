public static Map<String,String> convertProviderToMap(ProviderConfig providerConfig,ServerConfig server){
  Map<String,String> metaData=new HashMap<String,String>();
  metaData.put(RpcConstants.CONFIG_KEY_UNIQUEID,providerConfig.getUniqueId());
  metaData.put(RpcConstants.CONFIG_KEY_INTERFACE,providerConfig.getInterfaceId());
  metaData.put(RpcConstants.CONFIG_KEY_TIMEOUT,String.valueOf(providerConfig.getTimeout()));
  metaData.put(RpcConstants.CONFIG_KEY_DELAY,String.valueOf(providerConfig.getDelay()));
  metaData.put(RpcConstants.CONFIG_KEY_ID,providerConfig.getId());
  metaData.put(RpcConstants.CONFIG_KEY_DYNAMIC,String.valueOf(providerConfig.isDynamic()));
  metaData.put(ProviderInfoAttrs.ATTR_WEIGHT,String.valueOf(providerConfig.getWeight()));
  metaData.put(RpcConstants.CONFIG_KEY_ACCEPTS,String.valueOf(server.getAccepts()));
  metaData.put(ProviderInfoAttrs.ATTR_START_TIME,String.valueOf(RpcRuntimeContext.now()));
  metaData.put(RpcConstants.CONFIG_KEY_APP_NAME,providerConfig.getAppName());
  metaData.put(RpcConstants.CONFIG_KEY_SERIALIZATION,providerConfig.getSerialization());
  metaData.put(RpcConstants.CONFIG_KEY_PROTOCOL,server.getProtocol());
  if (null != providerConfig.getParameters()) {
    metaData.putAll(providerConfig.getParameters());
  }
  metaData.put(RpcConstants.CONFIG_KEY_LANGUAGE,JAVA);
  metaData.put(RpcConstants.CONFIG_KEY_PID,RpcRuntimeContext.PID);
  metaData.put(RpcConstants.CONFIG_KEY_RPC_VERSION,String.valueOf(Version.RPC_VERSION));
  return metaData;
}
