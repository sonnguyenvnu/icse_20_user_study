/** 
 * ????????
 * @param merchantNo
 * @return
 */
public List<Map<String,String>> getPayWayReport(String merchantNo){
  return rpTradePaymentRecordDao.getPayWayReport(merchantNo);
}
