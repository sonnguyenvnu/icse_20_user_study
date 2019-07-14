/** 
 * ??????????????????????
 */
public void validateScratchPool(){
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  String dateStr=sdf.format(DateUtil.addDay(new Date(),-3));
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("maxDate",dateStr);
  List<RpAccountCheckMistakeScratchPool> list=rpAccountCheckMistakeScratchPoolService.listScratchPoolRecord(paramMap);
  List<RpAccountCheckMistake> mistakeList=null;
  if (!list.isEmpty()) {
    mistakeList=new ArrayList<RpAccountCheckMistake>();
    for (    RpAccountCheckMistakeScratchPool scratchRecord : list) {
      RpAccountCheckMistake mistake=new RpAccountCheckMistake();
      mistake.setAccountCheckBatchNo(scratchRecord.getBatchNo());
      mistake.setBillDate(scratchRecord.getBillDate());
      mistake.setErrType(ReconciliationMistakeTypeEnum.BANK_MISS.name());
      mistake.setHandleStatus(MistakeHandleStatusEnum.NOHANDLE.name());
      mistake.setBankType(scratchRecord.getPayWayCode());
      mistake.setOrderNo(scratchRecord.getMerchantOrderNo());
      mistake.setTradeTime(scratchRecord.getPaySuccessTime());
      mistake.setTrxNo(scratchRecord.getTrxNo());
      mistake.setOrderAmount(scratchRecord.getOrderAmount());
      mistake.setRefundAmount(scratchRecord.getSuccessRefundAmount());
      mistake.setTradeStatus(scratchRecord.getStatus());
      mistake.setFee(scratchRecord.getPlatCost());
      mistakeList.add(mistake);
    }
    rpAccountCheckTransactionService.removeDateFromPool(list,mistakeList);
  }
}
