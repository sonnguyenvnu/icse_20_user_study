/** 
 * ???????
 * @param appId               ???ID
 * @param mchId               ???
 * @param productName         ????
 * @param remark              ????
 * @param bankOrderNo         ?????
 * @param orderPrice          ????
 * @param orderTime           ??????
 * @param orderPeriod         ?????
 * @param weiXinTradeTypeEnum ??????
 * @param productId           ??ID
 * @param openId              ????
 * @param orderIp             ??IP
 * @return
 */
private WeiXinPrePay sealWeixinPerPay(String appId,String mchId,String productName,String remark,String bankOrderNo,BigDecimal orderPrice,Date orderTime,Integer orderPeriod,WeiXinTradeTypeEnum weiXinTradeTypeEnum,String productId,String openId,String orderIp){
  WeiXinPrePay weiXinPrePay=new WeiXinPrePay();
  weiXinPrePay.setAppid(appId);
  weiXinPrePay.setMchId(mchId);
  weiXinPrePay.setBody(productName);
  weiXinPrePay.setAttach(remark);
  weiXinPrePay.setOutTradeNo(bankOrderNo);
  Integer totalFee=orderPrice.multiply(BigDecimal.valueOf(100d)).intValue();
  weiXinPrePay.setTotalFee(totalFee);
  weiXinPrePay.setTimeStart(DateUtils.formatDate(orderTime,"yyyyMMddHHmmss"));
  weiXinPrePay.setTimeExpire(DateUtils.formatDate(DateUtils.addMinute(orderTime,orderPeriod),"yyyyMMddHHmmss"));
  weiXinPrePay.setNotifyUrl(WeixinConfigUtil.readConfig("notify_url"));
  weiXinPrePay.setTradeType(weiXinTradeTypeEnum);
  weiXinPrePay.setProductId(productId);
  weiXinPrePay.setOpenid(openId);
  weiXinPrePay.setSpbillCreateIp(orderIp);
  return weiXinPrePay;
}
