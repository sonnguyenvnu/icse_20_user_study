/** 
 * Convert provider to url.
 * @param providerConfig the ProviderConfig
 * @return the url list
 */
public static String convertProviderToUrls(ProviderConfig providerConfig,ServerConfig server){
  StringBuilder sb=new StringBuilder(200);
  String appName=providerConfig.getAppName();
  String host=server.getVirtualHost();
  if (host == null) {
    host=server.getHost();
    if (NetUtils.isLocalHost(host) || NetUtils.isAnyHost(host)) {
      host=SystemInfo.getLocalHost();
    }
  }
 else {
    if (LOGGER.isWarnEnabled(appName)) {
      LOGGER.warnWithApp(appName,"Virtual host is specified, host will be change from {} to {} when register",server.getHost(),host);
    }
  }
  Integer port=server.getVirtualPort();
  if (port == null) {
    port=server.getPort();
  }
 else {
    if (LOGGER.isWarnEnabled(appName)) {
      LOGGER.warnWithApp(appName,"Virtual port is specified, host will be change from {} to {} when register",server.getPort(),port);
    }
  }
  String protocol=server.getProtocol();
  sb.append(host).append(":").append(port).append(server.getContextPath());
  sb.append("?").append(ATTR_RPC_VERSION).append("=").append(Version.RPC_VERSION);
  sb.append(getKeyPairs(ATTR_SERIALIZATION,providerConfig.getSerialization()));
  sb.append(getKeyPairs(ATTR_WEIGHT,providerConfig.getWeight()));
  if (providerConfig.getTimeout() > 0) {
    sb.append(getKeyPairs(ATTR_TIMEOUT,providerConfig.getTimeout()));
  }
  sb.append(getKeyPairs(ATTR_APP_NAME,appName));
  if (PROTOCOL_TYPE_BOLT.equals(protocol)) {
    sb.append(getKeyPairs(RPC_REMOTING_PROTOCOL,RemotingConstants.PROTOCOL_BOLT));
  }
 else   if (PROTOCOL_TYPE_TR.equals(protocol)) {
    sb.append(getKeyPairs(RPC_REMOTING_PROTOCOL,RemotingConstants.PROTOCOL_TR));
  }
  sb.append(getKeyPairs(RPC_SERVICE_VERSION,SOFA4_RPC_SERVICE_VERSION));
  sb.append(getKeyPairs(SERIALIZE_TYPE_KEY,providerConfig.getSerialization()));
  sb.append(getKeyPairs(WEIGHT_KEY,providerConfig.getWeight()));
  if (providerConfig.getTimeout() > 0) {
    sb.append(getKeyPairs(TIMEOUT,providerConfig.getTimeout()));
  }
  sb.append(getKeyPairs(APP_NAME,appName));
  if (StringUtils.isNotBlank(SystemInfo.getHostMachine())) {
    sb.append(getKeyPairs(HOST_MACHINE_KEY,SystemInfo.getHostMachine()));
  }
  Map<String,MethodConfig> methodConfigs=providerConfig.getMethods();
  if (CommonUtils.isNotEmpty(methodConfigs)) {
    for (    Map.Entry<String,MethodConfig> entry : methodConfigs.entrySet()) {
      String methodName=entry.getKey();
      MethodConfig methodConfig=entry.getValue();
      sb.append(getKeyPairs("." + methodName + "." + ATTR_TIMEOUT,methodConfig.getTimeout()));
      String key="[" + methodName + "]";
      String value="[" + KEY_TIMEOUT + "#" + methodConfig.getTimeout() + "]";
      sb.append(getKeyPairs(key,value));
    }
  }
  sb.append(convertMap2Pair(providerConfig.getParameters()));
  addCommonAttrs(sb);
  return sb.toString();
}
