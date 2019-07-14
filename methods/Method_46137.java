private static String convertInstanceToUrl(Instance instance){
  Map<String,String> metaData=instance.getMetadata();
  if (metaData == null) {
    metaData=new HashMap<String,String>();
  }
  String uri="";
  String protocol=metaData.get(RpcConstants.CONFIG_KEY_PROTOCOL);
  if (StringUtils.isNotEmpty(protocol)) {
    uri=protocol + "://";
  }
  uri+=instance.getIp() + ":" + instance.getPort();
  StringBuilder sb=new StringBuilder();
  for (  Map.Entry<String,String> entry : metaData.entrySet()) {
    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
  }
  if (sb.length() > 0) {
    uri+=sb.replace(0,1,"?").toString();
  }
  return uri;
}
