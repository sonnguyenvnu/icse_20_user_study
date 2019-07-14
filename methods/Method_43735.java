/** 
 * Sign request JSON. 
 */
protected JsonNode formAndSignRequestJson(Map<String,String> params){
  CoinbeneUtils.signParams(params);
  return toJson(params);
}
