/** 
 * ???????????????????
 * @param originUrl ?????
 * @return ProviderInfo??
 */
public static ProviderInfo parseProviderInfo(String originUrl){
  String url=originUrl;
  String host=null;
  int port=80;
  String path=null;
  String schema=null;
  int i=url.indexOf("://");
  if (i > 0) {
    schema=url.substring(0,i);
    url=url.substring(i + 3);
  }
  Map<String,String> parameters=new HashMap<String,String>();
  i=url.indexOf('?');
  if (i >= 0) {
    String[] parts=url.substring(i + 1).split("\\&");
    for (    String part : parts) {
      part=part.trim();
      if (part.length() > 0) {
        int j=part.indexOf('=');
        if (j >= 0) {
          parameters.put(part.substring(0,j),part.substring(j + 1));
        }
 else {
          parameters.put(part,part);
        }
      }
    }
    url=url.substring(0,i);
  }
  i=url.indexOf('/');
  if (i >= 0) {
    path=url.substring(i + 1);
    url=url.substring(0,i);
  }
  i=url.indexOf(':');
  if (i >= 0 && i < url.length() - 1) {
    port=Integer.parseInt(url.substring(i + 1));
    url=url.substring(0,i);
  }
  if (url.length() > 0) {
    host=url;
  }
  ProviderInfo providerInfo=new ProviderInfo();
  providerInfo.setOriginUrl(originUrl);
  providerInfo.setHost(host);
  if (port != 80) {
    providerInfo.setPort(port);
  }
  if (path != null) {
    providerInfo.setPath(path);
  }
  if (schema != null) {
    providerInfo.setProtocolType(schema);
  }
  String protocolStr=getValue(parameters,RPC_REMOTING_PROTOCOL);
  if (schema == null && protocolStr != null) {
    if ((RemotingConstants.PROTOCOL_BOLT + "").equals(protocolStr)) {
      protocolStr=PROTOCOL_TYPE_BOLT;
    }
 else     if ((RemotingConstants.PROTOCOL_TR + "").equals(protocolStr)) {
      protocolStr=PROTOCOL_TYPE_TR;
    }
    try {
      providerInfo.setProtocolType(protocolStr);
    }
 catch (    Exception e) {
      LOGGER.error("protocol is invalid : {}",originUrl);
    }
  }
  String timeoutStr=getValue(parameters,ATTR_TIMEOUT,TIMEOUT);
  if (timeoutStr != null) {
    removeOldKeys(parameters,ATTR_TIMEOUT,TIMEOUT);
    try {
      providerInfo.setDynamicAttr(ATTR_TIMEOUT,Integer.parseInt(timeoutStr));
    }
 catch (    Exception e) {
      LOGGER.error("timeout is invalid : {}",originUrl);
    }
  }
  String serializationStr=getValue(parameters,ATTR_SERIALIZATION,SERIALIZE_TYPE_KEY);
  if (serializationStr != null) {
    removeOldKeys(parameters,ATTR_SERIALIZATION,SERIALIZE_TYPE_KEY);
    if ((RemotingConstants.SERIALIZE_CODE_HESSIAN + "").equals(serializationStr)) {
      serializationStr=SERIALIZE_HESSIAN;
    }
 else     if ((RemotingConstants.SERIALIZE_CODE_JAVA + "").equals(serializationStr)) {
      serializationStr=SERIALIZE_JAVA;
    }
 else     if ((RemotingConstants.SERIALIZE_CODE_HESSIAN2 + "").equals(serializationStr)) {
      serializationStr=SERIALIZE_HESSIAN2;
    }
 else     if ((RemotingConstants.SERIALIZE_CODE_PROTOBUF + "").equals(serializationStr)) {
      serializationStr=SERIALIZE_PROTOBUF;
    }
    providerInfo.setSerializationType(serializationStr);
  }
  String appNameStr=getValue(parameters,ATTR_APP_NAME,APP_NAME,SofaRegistryConstants.SELF_APP_NAME);
  if (appNameStr != null) {
    removeOldKeys(parameters,APP_NAME,SofaRegistryConstants.SELF_APP_NAME);
    providerInfo.setStaticAttr(ATTR_APP_NAME,appNameStr);
  }
  String connections=getValue(parameters,ATTR_CONNECTIONS,SofaRegistryConstants.CONNECTI_NUM);
  if (connections != null) {
    removeOldKeys(parameters,SofaRegistryConstants.CONNECTI_NUM);
    providerInfo.setStaticAttr(ATTR_CONNECTIONS,connections);
  }
  String rpcVersion=getValue(parameters,ATTR_RPC_VERSION);
  providerInfo.setRpcVersion(CommonUtils.parseInt(rpcVersion,providerInfo.getRpcVersion()));
  String weightStr=getValue(parameters,ATTR_WEIGHT,WEIGHT_KEY);
  if (weightStr != null) {
    removeOldKeys(parameters,ATTR_WEIGHT,WEIGHT_KEY);
    try {
      int weight=Integer.parseInt(weightStr);
      providerInfo.setWeight(weight);
      providerInfo.setStaticAttr(ATTR_WEIGHT,weightStr);
    }
 catch (    Exception e) {
      LOGGER.error("weight is invalid : {}",originUrl);
    }
  }
  String warmupTimeStr=getValue(parameters,ATTR_WARMUP_TIME,SofaRegistryConstants.WARMUP_TIME_KEY);
  int warmupTime=0;
  if (warmupTimeStr != null) {
    removeOldKeys(parameters,ATTR_WARMUP_TIME,SofaRegistryConstants.WARMUP_TIME_KEY);
    try {
      warmupTime=Integer.parseInt(warmupTimeStr);
      providerInfo.setStaticAttr(ATTR_WARMUP_TIME,warmupTimeStr);
    }
 catch (    Exception e) {
      LOGGER.error("warmupTime is invalid : {}",originUrl);
    }
  }
  String warmupWeightStr=getValue(parameters,ATTR_WARMUP_WEIGHT,SofaRegistryConstants.WARMUP_WEIGHT_KEY);
  int warmupWeight=0;
  if (warmupWeightStr != null) {
    removeOldKeys(parameters,ATTR_WARMUP_WEIGHT,SofaRegistryConstants.WARMUP_WEIGHT_KEY);
    try {
      warmupWeight=Integer.parseInt(warmupWeightStr);
      providerInfo.setStaticAttr(ATTR_WARMUP_WEIGHT,warmupWeightStr);
    }
 catch (    Exception e) {
      LOGGER.error("warmupWeight is invalid : {}",originUrl);
    }
  }
  String startTimeStr=getValue(parameters,ATTR_START_TIME);
  long startTime=0L;
  if (startTimeStr != null) {
    try {
      startTime=Long.parseLong(startTimeStr);
    }
 catch (    Exception e) {
      LOGGER.error("startTime is invalid : {}",originUrl);
    }
  }
  if (startTime == 0) {
    startTime=System.currentTimeMillis();
  }
  if (StringUtils.isNotBlank(warmupTimeStr) && StringUtils.isNotBlank(warmupWeightStr)) {
    if (warmupTime > 0) {
      providerInfo.setStatus(ProviderStatus.WARMING_UP);
      providerInfo.setDynamicAttr(ATTR_WARMUP_WEIGHT,warmupWeight);
      providerInfo.setDynamicAttr(ATTR_WARM_UP_END_TIME,startTime + warmupTime);
    }
  }
  String hostMachineName=getValue(parameters,HOST_MACHINE_KEY);
  if (StringUtils.isNotBlank(hostMachineName)) {
    providerInfo.setDynamicAttr(ATTR_HOST_MACHINE,hostMachineName);
  }
  List<String> methodKeys=new ArrayList<String>();
  Map<String,Object> methodParameters=new HashMap<String,Object>();
  for (  Map.Entry<String,String> entry : parameters.entrySet()) {
    if (entry.getKey().startsWith("[") && entry.getKey().endsWith("]") && entry.getValue().startsWith("[") && entry.getValue().endsWith("]")) {
      String key=entry.getKey();
      methodKeys.add(key);
      String methodName=key.substring(1,key.length() - 1);
      parseMethodInfo(methodParameters,methodName,entry.getValue());
    }
  }
  for (  String methodKey : methodKeys) {
    parameters.remove(methodKey);
  }
  providerInfo.getStaticAttrs().putAll(parameters);
  providerInfo.getDynamicAttrs().putAll(methodParameters);
  providerInfo.setStaticAttr(ProviderInfoAttrs.ATTR_SOURCE,"sofa");
  return providerInfo;
}
