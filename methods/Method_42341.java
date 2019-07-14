/** 
 * ?????????????????[????]???
 * @param billDate ???
 * @param interfaceCode ????
 * @return
 */
public List<RpTradePaymentRecord> getSuccessPlatformDateByBillDate(Date billDate,String interfaceCode){
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  String billDateStr=sdf.format(billDate);
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("billDate",billDateStr);
  paramMap.put("interfaceCode",interfaceCode);
  paramMap.put("status",TradeStatusEnum.SUCCESS.name());
  LOG.info("??????????????billDate[" + billDateStr + "],?????[" + interfaceCode + "]");
  List<RpTradePaymentRecord> recordList=rpTradePaymentQueryService.listPaymentRecord(paramMap);
  if (recordList == null) {
    recordList=new ArrayList<RpTradePaymentRecord>();
  }
  LOG.info("???????count[" + recordList.size() + "]");
  return recordList;
}
