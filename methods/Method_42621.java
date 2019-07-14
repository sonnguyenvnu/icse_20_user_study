/** 
 * ????????
 * @param pageParam
 * @param paymentOrderQueryParam
 * @return
 */
@Override public PageBean<RpTradePaymentRecord> listPaymentRecordPage(PageParam pageParam,PaymentOrderQueryParam paymentOrderQueryParam){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("merchantNo",paymentOrderQueryParam.getMerchantNo());
  paramMap.put("merchantName",paymentOrderQueryParam.getMerchantName());
  paramMap.put("merchantOrderNo",paymentOrderQueryParam.getMerchantOrderNo());
  paramMap.put("fundIntoType",paymentOrderQueryParam.getFundIntoType());
  paramMap.put("merchantOrderNo",paymentOrderQueryParam.getOrderDateBegin());
  paramMap.put("merchantOrderNo",paymentOrderQueryParam.getOrderDateEnd());
  paramMap.put("payTypeName",paymentOrderQueryParam.getPayTypeName());
  paramMap.put("payWayName",paymentOrderQueryParam.getPayWayName());
  paramMap.put("status",paymentOrderQueryParam.getStatus());
  if (paymentOrderQueryParam.getOrderDateBegin() != null) {
    paramMap.put("orderDateBegin",paymentOrderQueryParam.getOrderDateBegin());
  }
  if (paymentOrderQueryParam.getOrderDateEnd() != null) {
    paramMap.put("orderDateEnd",paymentOrderQueryParam.getOrderDateEnd());
  }
  return rpTradePaymentRecordDao.listPage(pageParam,paramMap);
}
