/** 
 * ?????????
 * @param accountNo ????
 * @param requestNo ???
 * @param trxType ????
 * @return AccountHistory
 */
public RpAccountHistory getAccountHistoryByAccountNo_requestNo_trxType(String accountNo,String requestNo,Integer trxType){
  Map<String,Object> map=new HashMap<String,Object>();
  map.put("accountNo",accountNo);
  map.put("requestNo",requestNo);
  map.put("trxType",trxType);
  return rpAccountHistoryDao.getBy(map);
}
