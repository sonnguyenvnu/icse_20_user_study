/** 
 * Write provider info to url string
 * @param providerInfo Provide info
 * @return the string
 */
public static String toUrl(ProviderInfo providerInfo){
  String uri=providerInfo.getProtocolType() + "://" + providerInfo.getHost() + ":" + providerInfo.getPort();
  uri+=StringUtils.trimToEmpty(providerInfo.getPath());
  StringBuilder sb=new StringBuilder();
  if (providerInfo.getRpcVersion() > 0) {
    sb.append("&").append(ProviderInfoAttrs.ATTR_RPC_VERSION).append("=").append(providerInfo.getRpcVersion());
  }
  if (providerInfo.getSerializationType() != null) {
    sb.append("&").append(ProviderInfoAttrs.ATTR_SERIALIZATION).append("=").append(providerInfo.getSerializationType());
  }
  for (  Map.Entry<String,String> entry : providerInfo.getStaticAttrs().entrySet()) {
    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
  }
  if (sb.length() > 0) {
    uri+=sb.replace(0,1,"?").toString();
  }
  return uri;
}
