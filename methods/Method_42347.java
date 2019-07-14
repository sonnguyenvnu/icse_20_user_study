/** 
 * ?????????????????????
 * @param interfaceCode ????
 * @param billDate ???
 * @return
 */
public Boolean isChecked(String interfaceCode,Date billDate){
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  String billDateStr=sdf.format(billDate);
  LOG.info("??,????[" + interfaceCode + "],????[" + billDateStr + "]");
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("billDate",billDateStr);
  paramMap.put("interfaceCode",interfaceCode);
  paramMap.put("status",BatchStatusEnum.ERROR.name() + "," + BatchStatusEnum.FAIL.name());
  List<RpAccountCheckBatch> list=rpAccountCheckBatchService.listBy(paramMap);
  if (list.isEmpty()) {
    return false;
  }
  return true;
}
