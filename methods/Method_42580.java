/** 
 * ????????????????????
 * @param merchantNo    ????
 * @param merchantOrderNo   ?????
 * @return
 */
@Override public RpTradePaymentOrder selectByMerchantNoAndMerchantOrderNo(String merchantNo,String merchantOrderNo){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("merchantNo",merchantNo);
  paramMap.put("merchantOrderNo",merchantOrderNo);
  return super.getBy(paramMap);
}
