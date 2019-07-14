/** 
 * ?????????????????
 * @param rpTradePaymentOrder ????
 * @param payWay              ??????
 * @return
 */
private F2FPayResultVo getF2FPayResultVo(RpTradePaymentOrder rpTradePaymentOrder,RpPayWay payWay,String payKey,String merchantPaySecret,String authCode,List<RoncooPayGoodsDetails> roncooPayGoodsDetailses){
  F2FPayResultVo f2FPayResultVo=new F2FPayResultVo();
  String payWayCode=payWay.getPayWayCode();
  PayTypeEnum payType=null;
  if (PayWayEnum.WEIXIN.name().equals(payWay.getPayWayCode())) {
    payType=PayTypeEnum.MICRO_PAY;
  }
 else   if (PayWayEnum.ALIPAY.name().equals(payWay.getPayWayCode())) {
    payType=PayTypeEnum.F2F_PAY;
  }
  rpTradePaymentOrder.setPayTypeCode(payType.name());
  rpTradePaymentOrder.setPayTypeName(payType.getDesc());
  rpTradePaymentOrder.setPayWayCode(payWay.getPayWayCode());
  rpTradePaymentOrder.setPayWayName(payWay.getPayWayName());
  RpTradePaymentRecord rpTradePaymentRecord=sealRpTradePaymentRecord(rpTradePaymentOrder.getMerchantNo(),rpTradePaymentOrder.getMerchantName(),rpTradePaymentOrder.getProductName(),rpTradePaymentOrder.getMerchantOrderNo(),rpTradePaymentOrder.getOrderAmount(),payWay.getPayWayCode(),payWay.getPayWayName(),payType,rpTradePaymentOrder.getFundIntoType(),BigDecimal.valueOf(payWay.getPayRate()),rpTradePaymentOrder.getOrderIp(),rpTradePaymentOrder.getReturnUrl(),rpTradePaymentOrder.getNotifyUrl(),rpTradePaymentOrder.getRemark(),rpTradePaymentOrder.getField1(),rpTradePaymentOrder.getField2(),rpTradePaymentOrder.getField3(),rpTradePaymentOrder.getField4(),rpTradePaymentOrder.getField5());
  rpTradePaymentRecordDao.insert(rpTradePaymentRecord);
  if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {
    RpUserPayInfo rpUserPayInfo=rpUserPayInfoService.getByUserNo(rpTradePaymentOrder.getMerchantNo(),payWayCode);
    if (rpUserPayInfo == null) {
      throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
    }
    Map<String,Object> wxResultMap=WeiXinPayUtil.micropay(rpTradePaymentRecord.getBankOrderNo(),rpTradePaymentOrder.getProductName(),rpTradePaymentRecord.getOrderAmount(),rpTradePaymentRecord.getOrderIp(),authCode);
    if (wxResultMap == null || wxResultMap.isEmpty()) {
      rpNotifyService.orderSend(rpTradePaymentRecord.getBankOrderNo());
    }
 else {
      if ("YES".equals(wxResultMap.get("verify"))) {
        if ("SUCCESS".equals(wxResultMap.get("return_code")) && "SUCCESS".equals(wxResultMap.get("result_code"))) {
          completeSuccessOrder(rpTradePaymentRecord,String.valueOf(wxResultMap.get("transaction_id")),new Date(),"????");
        }
 else         if ("SUCCESS".equals(wxResultMap.get("return_code")) && !StringUtil.isEmpty(wxResultMap.get("err_code")) && !"BANKERROR".equals(wxResultMap.get("err_code")) && !"USERPAYING".equals(wxResultMap.get("err_code")) && !"SYSTEMERROR".equals(wxResultMap.get("err_code"))) {
          completeFailOrder(rpTradePaymentRecord,String.valueOf(wxResultMap.get("err_code_des")));
        }
 else {
          rpNotifyService.orderSend(rpTradePaymentRecord.getBankOrderNo());
        }
      }
 else {
        completeFailOrder(rpTradePaymentRecord,"??????!");
      }
    }
  }
 else   if (PayWayEnum.ALIPAY.name().equals(payWayCode)) {
    RpUserPayInfo rpUserPayInfo=rpUserPayInfoService.getByUserNo(rpTradePaymentOrder.getMerchantNo(),payWayCode);
    if (rpUserPayInfo == null) {
      throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
    }
    Map<String,Object> resultMap=AliPayUtil.tradePay(rpTradePaymentRecord.getBankOrderNo(),authCode,rpTradePaymentOrder.getProductName(),rpTradePaymentRecord.getOrderAmount(),"",roncooPayGoodsDetailses);
    rpNotifyService.orderSend(rpTradePaymentRecord.getBankOrderNo());
  }
 else {
    throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR,"???????");
  }
  Map<String,Object> paramMap=new HashMap<String,Object>();
  f2FPayResultVo.setStatus(rpTradePaymentRecord.getStatus());
  paramMap.put("status",rpTradePaymentRecord.getStatus());
  f2FPayResultVo.setField1(rpTradePaymentRecord.getField1());
  paramMap.put("field1",rpTradePaymentRecord.getField1());
  f2FPayResultVo.setField2(rpTradePaymentRecord.getField2());
  paramMap.put("field2",rpTradePaymentRecord.getField2());
  f2FPayResultVo.setField3(rpTradePaymentRecord.getField3());
  paramMap.put("field3",rpTradePaymentRecord.getField3());
  f2FPayResultVo.setField4(rpTradePaymentRecord.getField4());
  paramMap.put("field4",rpTradePaymentRecord.getField4());
  f2FPayResultVo.setField5(rpTradePaymentRecord.getField5());
  paramMap.put("field5",rpTradePaymentRecord.getField5());
  f2FPayResultVo.setOrderIp(rpTradePaymentRecord.getOrderIp());
  paramMap.put("orderIp",rpTradePaymentRecord.getOrderIp());
  f2FPayResultVo.setOrderNo(rpTradePaymentRecord.getMerchantOrderNo());
  paramMap.put("merchantOrderNo",rpTradePaymentRecord.getMerchantOrderNo());
  f2FPayResultVo.setPayKey(payKey);
  paramMap.put("payKey",payKey);
  f2FPayResultVo.setProductName(rpTradePaymentRecord.getProductName());
  paramMap.put("productName",rpTradePaymentRecord.getProductName());
  f2FPayResultVo.setRemark(rpTradePaymentRecord.getRemark());
  paramMap.put("remark",rpTradePaymentRecord.getRemark());
  f2FPayResultVo.setTrxNo(rpTradePaymentRecord.getTrxNo());
  paramMap.put("trxNo",rpTradePaymentRecord.getTrxNo());
  String sign=MerchantApiUtil.getSign(paramMap,merchantPaySecret);
  f2FPayResultVo.setSign(sign);
  return f2FPayResultVo;
}
