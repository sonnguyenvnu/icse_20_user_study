/** 
 * ?????????????
 * @param trxNo
 * @return
 */
public RpTradePaymentRecord getByTrxNo(String trxNo){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("trxNo",trxNo);
  return super.getBy(paramMap);
}
