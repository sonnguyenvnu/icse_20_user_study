public String digestParamsToString(ExchangeEndpoint endpoint,Long nonce,Map<String,Object> parameters){
  Mac mac256=getMac();
  String url=ExchangeEndpoint.getUrlBasingOnEndpoint(sslUri,endpoint) + ",";
  logger.debug("digestParamsToString: url: {}",url);
  mac256.update(url.getBytes());
  String namedApiKey="apiKey=" + apiKey + ",";
  logger.debug("digestParamsToString: apiKey: {}",namedApiKey);
  mac256.update(namedApiKey.getBytes());
  String namedNonce="nonce=" + nonce.toString();
  logger.debug("digestParamsToString: nonce: {}",namedNonce);
  mac256.update(namedNonce.getBytes());
  if (parameters != null && parameters.size() > 0) {
    List<String> namedParameters=new ArrayList<>();
    for (    Map.Entry<String,Object> parameter : parameters.entrySet()) {
      Object value=parameter.getValue();
      StringBuilder valueStr;
      if (value == null) {
        valueStr=null;
      }
 else       if (value instanceof Object[]) {
        valueStr=new StringBuilder();
        for (        Object o : (Object[])value) {
          if (valueStr.length() != 0) {
            valueStr.append(',');
          }
          valueStr.append(String.valueOf(o));
        }
      }
 else {
        valueStr=new StringBuilder(String.valueOf(value));
      }
      String namedParameter=parameter.getKey() + "=" + valueStr;
      namedParameters.add(namedParameter);
    }
    StringBuilder joinedNamedParameters=new StringBuilder();
    for (    String namedParameter : namedParameters) {
      joinedNamedParameters.append(namedParameter).append(",");
    }
    joinedNamedParameters=new StringBuilder(joinedNamedParameters.substring(0,joinedNamedParameters.length() - 1));
    if (!joinedNamedParameters.toString().equals("")) {
      joinedNamedParameters.insert(0,",");
      mac256.update(joinedNamedParameters.toString().getBytes());
    }
  }
  return String.format("%064x",new BigInteger(1,mac256.doFinal())).toUpperCase();
}
