/** 
 * ?????????? .
 * @param accountNo ????
 * @param statDate ????
 * @param riskDay ?????
 * @param fundDirection ????
 * @return
 */
public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(String accountNo,String statDate,Integer riskDay,Integer fundDirection){
  Map<String,Object> params=new HashMap<String,Object>();
  params.put("accountNo",accountNo);
  params.put("statDate",statDate);
  params.put("riskDay",riskDay);
  params.put("fundDirection",fundDirection);
  return rpAccountHistoryDao.listDailyCollectAccountHistoryVo(params);
}
