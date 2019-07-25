public Map<String,String> build(){
  Map<String,String> map=new HashMap<String,String>();
  if (getPayModel().equals(PayModel.SERVICEMODE)) {
    map.put("sub_mch_id",getSubMchId());
    map.put("msgappid",getMsgAppId());
  }
  if (getTotalAmount() > 20000) {
    map.put("scene_id",getSceneId().name());
  }
  if (StrKit.notBlank(getRiskInfo())) {
    map.put("risk_info",getRiskInfo());
  }
  if (StrKit.notBlank(getConsumeMchId())) {
    map.put("consume_mch_id",getConsumeMchId());
  }
  if (getHbType().equals(hbType.NORMAL)) {
    map.put("client_ip",getClientIp());
  }
 else {
    map.put("amt_type",getAmtType().name());
  }
  map.put("nonce_str",getNonceStr());
  map.put("mch_billno",getMchBillNo());
  map.put("mch_id",getMchId());
  map.put("wxappid",getWxAppId());
  map.put("send_name",getSendName());
  map.put("re_openid",getReOpenId());
  map.put("total_amount",String.valueOf(getTotalAmount()));
  map.put("total_num",String.valueOf(getTotalNum()));
  map.put("wishing",getWishing());
  map.put("act_name",getActName());
  map.put("remark",getRemark());
  map.put("sign",PaymentKit.createSign(map,getPaternerKey()));
  return map;
}
