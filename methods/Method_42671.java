/** 
 * ????????
 * @param appid       ????ID
 * @param mch_id      ???
 * @param device_info ???
 * @param trade_type  ????
 * @param prePay      ?????Map
 * @param partnerKey  ??EY
 * @return
 */
public static String geWeiXintPrePaySign(String appid,String mch_id,String device_info,String trade_type,Map<String,Object> prePay,String partnerKey){
  Map<String,Object> preParams=new HashMap<String,Object>();
  if (!StringUtil.isEmpty(prePay.get("return_code"))) {
    preParams.put("return_code",prePay.get("return_code"));
  }
  if (!StringUtil.isEmpty(prePay.get("return_msg"))) {
    preParams.put("return_msg",prePay.get("return_msg"));
  }
  if (!StringUtil.isEmpty(prePay.get("appid"))) {
    preParams.put("appid",appid);
  }
  if (!StringUtil.isEmpty(prePay.get("mch_id"))) {
    preParams.put("mch_id",mch_id);
  }
  if (!StringUtil.isEmpty(prePay.get("device_info"))) {
    preParams.put("device_info",device_info);
  }
  if (!StringUtil.isEmpty(prePay.get("nonce_str"))) {
    preParams.put("nonce_str",prePay.get("nonce_str"));
  }
  if (!StringUtil.isEmpty(prePay.get("result_code"))) {
    preParams.put("result_code",prePay.get("result_code"));
  }
  if ("FAIL".equals(prePay.get("result_code"))) {
    if (!StringUtil.isEmpty(prePay.get("err_code"))) {
      preParams.put("err_code",prePay.get("err_code"));
    }
    if (!StringUtil.isEmpty(prePay.get("err_code_des"))) {
      preParams.put("err_code_des",prePay.get("err_code_des"));
    }
  }
  if (!StringUtil.isEmpty(prePay.get("trade_type"))) {
    preParams.put("trade_type",trade_type);
  }
  if (!StringUtil.isEmpty(prePay.get("prepay_id"))) {
    preParams.put("prepay_id",prePay.get("prepay_id"));
  }
  if (!StringUtil.isEmpty(prePay.get("code_url"))) {
    preParams.put("code_url",prePay.get("code_url"));
  }
  String argPreSign=getStringByMap(preParams) + "&key=" + partnerKey;
  String preSign=MD5Util.encode(argPreSign).toUpperCase();
  return preSign;
}
