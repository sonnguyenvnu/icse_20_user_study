/** 
 * ???????????xml??
 * @param appId ??
 * @param mchId ??
 * @param billDate ??, ????????(?????)
 * @param billType ?????
 * @param appSecret ??, ?????
 * @return
 */
public String generateXml(){
  HashMap<String,String> params=new HashMap<String,String>();
  params.put("appid",appid);
  params.put("mch_id",mch_id);
  params.put("bill_date",bill_date);
  params.put("bill_type",bill_type);
  params.put("nonce_str",WeiXinBaseUtils.createNoncestr());
  for (Iterator<Entry<String,String>> it=params.entrySet().iterator(); it.hasNext(); ) {
    Entry<String,String> entry=it.next();
    if (StringUtils.isEmpty(entry.getValue())) {
      it.remove();
    }
  }
  String sign=SignHelper.getSign(params,appSecret);
  params.put("sign",sign.toUpperCase());
  return WeiXinBaseUtils.arrayToXml(params);
}
