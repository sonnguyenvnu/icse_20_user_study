/** 
 * ????????
 * @param pid
 * @param app_id
 * @param target_id
 * @return
 */
public static Map<String,String> buildAuthInfoMap(String pid,String app_id,String target_id,boolean rsa2){
  Map<String,String> keyValues=new HashMap<String,String>();
  keyValues.put("app_id",app_id);
  keyValues.put("pid",pid);
  keyValues.put("apiname","com.alipay.account.auth");
  keyValues.put("app_name","mc");
  keyValues.put("biz_type","openservice");
  keyValues.put("product_id","APP_FAST_LOGIN");
  keyValues.put("scope","kuaijie");
  keyValues.put("target_id",target_id);
  keyValues.put("auth_type","AUTHACCOUNT");
  keyValues.put("sign_type",rsa2 ? "RSA2" : "RSA");
  return keyValues;
}
