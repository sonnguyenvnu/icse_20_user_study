/** 
 * ?????? ????????????????,???????,??TRUE ????????????,??FALSE
 * @param bankOrderNo ?????
 * @return
 */
@Override public boolean processingTradeRecord(String bankOrderNo){
  RpTradePaymentRecord byBankOrderNo=rpTradePaymentRecordDao.getByBankOrderNo(bankOrderNo);
  if (byBankOrderNo == null) {
    LOG.info("?????????[{}]???????",bankOrderNo);
    throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR,"?????");
  }
  LOG.info("???:[{}],?????[{}]",byBankOrderNo.getBankOrderNo(),byBankOrderNo.getPayWayCode());
  if (!TradeStatusEnum.WAITING_PAYMENT.name().equals(byBankOrderNo.getStatus())) {
    LOG.info("??????[{}]??????????:{},??????",bankOrderNo,byBankOrderNo.getStatus());
    return true;
  }
  if (byBankOrderNo.getPayWayCode().equals(PayWayEnum.WEIXIN.name())) {
    Map<String,Object> resultMap;
    if (PayTypeEnum.WX_PROGRAM_PAY.name().equals(byBankOrderNo.getPayTypeCode())) {
      LOG.info("??--???????!???:[{}]",byBankOrderNo.getBankOrderNo());
      resultMap=WeiXinPayUtils.orderQuery(byBankOrderNo.getBankOrderNo(),WeixinConfigUtil.xAppId,WeixinConfigUtil.xMchId,WeixinConfigUtil.xPayKey);
    }
 else {
      LOG.info("??--????!???:[{}]",byBankOrderNo.getBankOrderNo());
      resultMap=WeiXinPayUtils.orderQuery(byBankOrderNo.getBankOrderNo(),WeixinConfigUtil.appId,WeixinConfigUtil.mch_id,WeixinConfigUtil.partnerKey);
    }
    LOG.info("????????:{}",resultMap.toString());
    if (resultMap == null || resultMap.isEmpty()) {
      return false;
    }
    Object returnCode=resultMap.get("return_code");
    if (null == returnCode || "FAIL".equals(returnCode)) {
      return false;
    }
    if ("SUCCESS".equals(resultMap.get("trade_state"))) {
      completeSuccessOrder(byBankOrderNo,byBankOrderNo.getBankTrxNo(),new Date(),"??????");
      return true;
    }
    return false;
  }
  if (byBankOrderNo.getPayWayCode().equals(PayWayEnum.ALIPAY.name())) {
    if (PayTypeEnum.DIRECT_PAY.name().equals(byBankOrderNo.getPayTypeCode())) {
      LOG.info("???--????????!???:[{}]",byBankOrderNo.getBankOrderNo());
      Map<String,Object> resultMap=AliPayUtil.singleTradeQuery(byBankOrderNo.getBankOrderNo());
      if (resultMap.isEmpty() || !"T".equals(resultMap.get("is_success"))) {
        return false;
      }
      if ("TRADE_SUCCESS".equals(resultMap.get("trade_status")) || "TRADE_FINISHED".equals(resultMap.get("trade_status"))) {
        completeSuccessOrder(byBankOrderNo,byBankOrderNo.getBankTrxNo(),new Date(),"??????");
        return true;
      }
    }
 else     if (PayTypeEnum.F2F_PAY.name().equals(byBankOrderNo.getPayTypeCode())) {
      LOG.info("???--????????!???:[{}]",byBankOrderNo.getBankOrderNo());
      Map<String,Object> resultMap=AliPayUtil.tradeQuery(byBankOrderNo.getBankOrderNo());
      if (!"10000".equals(resultMap.get("code"))) {
        return false;
      }
      if ("TRADE_SUCCESS".equals(resultMap.get("tradeStatus")) || "TRADE_FINISHED".equals(resultMap.get("tradeStatus"))) {
        completeSuccessOrder(byBankOrderNo,byBankOrderNo.getBankTrxNo(),new Date(),"??????");
        return true;
      }
      return false;
    }
  }
  return false;
}
