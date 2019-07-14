/** 
 * ??????????List
 * @param paramMap
 * @return
 */
public List<RpTradePaymentRecord> listPaymentRecord(Map<String,Object> paramMap){
  return rpTradePaymentRecordDao.listByColumn(paramMap);
}
