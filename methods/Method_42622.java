/** 
 * ????????
 * @param merchantNo
 * @return
 */
public List<Map<String,String>> getPaymentReport(String merchantNo){
  return rpTradePaymentRecordDao.getPaymentReport(merchantNo);
}
