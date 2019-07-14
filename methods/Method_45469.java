/** 
 * Parse url string to ProviderInfo.
 * @param url the url
 * @return ProviderInfo 
 */
public static ProviderInfo toProviderInfo(String url){
  ProviderInfo providerInfo=new ProviderInfo();
  providerInfo.setOriginUrl(url);
  try {
    int protocolIndex=url.indexOf("://");
    String remainUrl;
    if (protocolIndex > -1) {
      String protocol=url.substring(0,protocolIndex).toLowerCase();
      providerInfo.setProtocolType(protocol);
      remainUrl=url.substring(protocolIndex + 3);
    }
 else {
      remainUrl=url;
    }
    int addressIndex=remainUrl.indexOf(StringUtils.CONTEXT_SEP);
    String address;
    if (addressIndex > -1) {
      address=remainUrl.substring(0,addressIndex);
      remainUrl=remainUrl.substring(addressIndex);
    }
 else {
      int itfIndex=remainUrl.indexOf('?');
      if (itfIndex > -1) {
        address=remainUrl.substring(0,itfIndex);
        remainUrl=remainUrl.substring(itfIndex);
      }
 else {
        address=remainUrl;
        remainUrl="";
      }
    }
    String[] ipAndPort=address.split(":",-1);
    providerInfo.setHost(ipAndPort[0]);
    if (ipAndPort.length > 1) {
      providerInfo.setPort(CommonUtils.parseInt(ipAndPort[1],providerInfo.getPort()));
    }
    if (remainUrl.length() > 0) {
      int itfIndex=remainUrl.indexOf('?');
      if (itfIndex > -1) {
        String itf=remainUrl.substring(0,itfIndex);
        providerInfo.setPath(itf);
        remainUrl=remainUrl.substring(itfIndex + 1);
        String[] params=remainUrl.split("&",-1);
        for (        String parm : params) {
          String[] kvpair=parm.split("=",-1);
          if (ProviderInfoAttrs.ATTR_WEIGHT.equals(kvpair[0]) && StringUtils.isNotEmpty(kvpair[1])) {
            int weight=CommonUtils.parseInt(kvpair[1],providerInfo.getWeight());
            providerInfo.setWeight(weight);
            providerInfo.setStaticAttr(ProviderInfoAttrs.ATTR_WEIGHT,String.valueOf(weight));
          }
 else           if (ProviderInfoAttrs.ATTR_RPC_VERSION.equals(kvpair[0]) && StringUtils.isNotEmpty(kvpair[1])) {
            providerInfo.setRpcVersion(CommonUtils.parseInt(kvpair[1],providerInfo.getRpcVersion()));
          }
 else           if (ProviderInfoAttrs.ATTR_SERIALIZATION.equals(kvpair[0]) && StringUtils.isNotEmpty(kvpair[1])) {
            providerInfo.setSerializationType(kvpair[1]);
          }
 else {
            providerInfo.getStaticAttrs().put(kvpair[0],kvpair[1]);
          }
        }
      }
 else {
        providerInfo.setPath(remainUrl);
      }
    }
 else {
      providerInfo.setPath(StringUtils.EMPTY);
    }
  }
 catch (  Exception e) {
    throw new IllegalArgumentException("Failed to convert url to provider, the wrong url is:" + url,e);
  }
  return providerInfo;
}
