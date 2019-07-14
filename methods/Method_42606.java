private String getMerchantNotifyUrl(RpTradePaymentRecord rpTradePaymentRecord,RpTradePaymentOrder rpTradePaymentOrder,String sourceUrl,TradeStatusEnum tradeStatusEnum){
  RpUserPayConfig rpUserPayConfig=rpUserPayConfigService.getByUserNo(rpTradePaymentRecord.getMerchantNo());
  if (rpUserPayConfig == null) {
    throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
  }
  Map<String,Object> paramMap=new HashMap<>();
  String payKey=rpUserPayConfig.getPayKey();
  paramMap.put("payKey",payKey);
  String productName=rpTradePaymentRecord.getProductName();
  paramMap.put("productName",productName);
  String orderNo=rpTradePaymentRecord.getMerchantOrderNo();
  paramMap.put("orderNo",orderNo);
  BigDecimal orderPrice=rpTradePaymentRecord.getOrderAmount();
  paramMap.put("orderPrice",orderPrice);
  String payWayCode=rpTradePaymentRecord.getPayWayCode();
  paramMap.put("payWayCode",payWayCode);
  paramMap.put("tradeStatus",tradeStatusEnum);
  String orderDateStr=DateUtils.formatDate(rpTradePaymentOrder.getOrderDate(),"yyyyMMdd");
  paramMap.put("orderDate",orderDateStr);
  String orderTimeStr=DateUtils.formatDate(rpTradePaymentOrder.getOrderTime(),"yyyyMMddHHmmss");
  paramMap.put("orderTime",orderTimeStr);
  String remark=rpTradePaymentRecord.getRemark();
  paramMap.put("remark",remark);
  String trxNo=rpTradePaymentRecord.getTrxNo();
  paramMap.put("trxNo",trxNo);
  String field1=rpTradePaymentOrder.getField1();
  paramMap.put("field1",field1);
  String field2=rpTradePaymentOrder.getField2();
  paramMap.put("field2",field2);
  String field3=rpTradePaymentOrder.getField3();
  paramMap.put("field3",field3);
  String field4=rpTradePaymentOrder.getField4();
  paramMap.put("field4",field4);
  String field5=rpTradePaymentOrder.getField5();
  paramMap.put("field5",field5);
  String paramStr=MerchantApiUtil.getParamStr(paramMap);
  String sign=MerchantApiUtil.getSign(paramMap,rpUserPayConfig.getPaySecret());
  String notifyUrl=sourceUrl + "?" + paramStr + "&sign=" + sign;
  return notifyUrl;
}
