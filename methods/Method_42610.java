/** 
 * ??????(?????????)
 * @param payWayCode
 * @param notifyMap
 * @return
 */
@Override @Transactional(rollbackFor=Exception.class) public String completeScanPay(String payWayCode,Map<String,String> notifyMap){
  LOG.info("???{}????{}",payWayCode,notifyMap);
  String returnStr=null;
  String bankOrderNo=notifyMap.get("out_trade_no");
  RpTradePaymentRecord rpTradePaymentRecord=rpTradePaymentRecordDao.getByBankOrderNo(bankOrderNo);
  if (rpTradePaymentRecord == null) {
    throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR,",????,?????");
  }
  if (TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentRecord.getStatus())) {
    throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR,"???????");
  }
  String merchantNo=rpTradePaymentRecord.getMerchantNo();
  String fundIntoType=rpTradePaymentRecord.getFundIntoType();
  String partnerKey="";
  if (FundInfoTypeEnum.MERCHANT_RECEIVES.name().equals(fundIntoType)) {
    RpUserPayInfo rpUserPayInfo=rpUserPayInfoService.getByUserNo(merchantNo,PayWayEnum.WEIXIN.name());
    partnerKey=rpUserPayInfo.getPartnerKey();
  }
 else   if (FundInfoTypeEnum.PLAT_RECEIVES.name().equals(fundIntoType)) {
    partnerKey=WeixinConfigUtil.readConfig("partnerKey");
    RpUserPayConfig rpUserPayConfig=rpUserPayConfigService.getByUserNo(merchantNo);
    if (rpUserPayConfig == null) {
      throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
    }
    RpPayWay payWay=rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(),rpTradePaymentRecord.getPayWayCode(),rpTradePaymentRecord.getPayTypeCode());
    if (payWay == null) {
      throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
    }
  }
  if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {
    String sign=notifyMap.remove("sign");
    if (WeiXinPayUtils.notifySign(notifyMap,sign,partnerKey)) {
      if (WeixinTradeStateEnum.SUCCESS.name().equals(notifyMap.get("result_code"))) {
        String timeEndStr=notifyMap.get("time_end");
        Date timeEnd=null;
        if (!StringUtil.isEmpty(timeEndStr)) {
          timeEnd=DateUtils.getDateFromString(timeEndStr,"yyyyMMddHHmmss");
        }
        completeSuccessOrder(rpTradePaymentRecord,notifyMap.get("transaction_id"),timeEnd,notifyMap.toString());
        returnStr="<xml>\n" + "  <return_code><![CDATA[SUCCESS]]></return_code>\n" + "  <return_msg><![CDATA[OK]]></return_msg>\n" + "</xml>";
      }
 else {
        completeFailOrder(rpTradePaymentRecord,notifyMap.toString());
      }
    }
 else {
      throw new TradeBizException(TradeBizException.TRADE_WEIXIN_ERROR,"??????");
    }
  }
 else   if ("WEIXIN_PROGRAM".equals(payWayCode)) {
    String sign=notifyMap.remove("sign");
    if (WeiXinPayUtils.notifySign(notifyMap,sign,WeixinConfigUtil.xPayKey)) {
      if (WeixinTradeStateEnum.SUCCESS.name().equals(notifyMap.get("result_code"))) {
        String timeEndStr=notifyMap.get("time_end");
        Date timeEnd=null;
        if (!StringUtil.isEmpty(timeEndStr)) {
          timeEnd=DateUtils.getDateFromString(timeEndStr,"yyyyMMddHHmmss");
        }
        completeSuccessOrder(rpTradePaymentRecord,notifyMap.get("transaction_id"),timeEnd,notifyMap.toString());
        returnStr="<xml>\n" + "  <return_code><![CDATA[SUCCESS]]></return_code>\n" + "  <return_msg><![CDATA[OK]]></return_msg>\n" + "</xml>";
      }
 else {
        completeFailOrder(rpTradePaymentRecord,notifyMap.toString());
      }
    }
 else {
      throw new TradeBizException(TradeBizException.TRADE_WEIXIN_ERROR,"??????");
    }
  }
 else   if (PayWayEnum.ALIPAY.name().equals(payWayCode)) {
    if (AlipayNotify.verify(notifyMap)) {
      String tradeStatus=notifyMap.get("trade_status");
      if (AliPayTradeStateEnum.TRADE_FINISHED.name().equals(tradeStatus)) {
      }
 else       if (AliPayTradeStateEnum.TRADE_SUCCESS.name().equals(tradeStatus)) {
        String gmtPaymentStr=notifyMap.get("gmt_payment");
        Date timeEnd=null;
        if (!StringUtil.isEmpty(gmtPaymentStr)) {
          timeEnd=DateUtils.getDateFromString(gmtPaymentStr,"yyyy-MM-dd HH:mm:ss");
        }
        completeSuccessOrder(rpTradePaymentRecord,notifyMap.get("trade_no"),timeEnd,notifyMap.toString());
        returnStr="success";
      }
 else {
        completeFailOrder(rpTradePaymentRecord,notifyMap.toString());
        returnStr="fail";
      }
    }
 else {
      throw new TradeBizException(TradeBizException.TRADE_ALIPAY_ERROR,"???????");
    }
  }
 else {
    throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR,"???????");
  }
  LOG.info("??????{}??{}",payWayCode,returnStr);
  return returnStr;
}
