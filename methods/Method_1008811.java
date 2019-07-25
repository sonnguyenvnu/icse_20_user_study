/** 
 * ????????
 * @param configParam
 * @return
 */
public AlipayConfig init(String configParam){
  Assert.notNull(configParam,"init alipay config error");
  JSONObject paramObj=JSON.parseObject(configParam);
  this.setApp_id(paramObj.getString("appid"));
  this.setRsa_private_key(paramObj.getString("private_key"));
  this.setAlipay_public_key(paramObj.getString("alipay_public_key"));
  this.setIsSandbox(paramObj.getShortValue("isSandbox"));
  if (this.getIsSandbox() == 1)   this.setUrl("https://openapi.alipaydev.com/gateway.do");
  return this;
}
