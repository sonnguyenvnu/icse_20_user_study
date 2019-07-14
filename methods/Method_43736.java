/** 
 * Return private API common params. 
 */
protected Map<String,String> getCommonParams(){
  Map<String,String> params=new TreeMap<>();
  params.put("apiid",apiKey);
  params.put("secret",secretKey);
  params.put("timestamp",String.valueOf(exchange.getNonceFactory().createValue()));
  return params;
}
