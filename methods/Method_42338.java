/** 
 * ??????
 * @param bankList ???????????
 * @param interfaceCode ????
 * @param batch ??????
 */
public void check(List<ReconciliationEntityVo> bankList,String interfaceCode,RpAccountCheckBatch batch){
  if (bankList == null) {
    bankList=new ArrayList<ReconciliationEntityVo>();
  }
  List<RpTradePaymentRecord> platSucessDateList=reconciliationDataGetBiz.getSuccessPlatformDateByBillDate(batch.getBillDate(),interfaceCode);
  List<RpTradePaymentRecord> platAllDateList=reconciliationDataGetBiz.getAllPlatformDateByBillDate(batch.getBillDate(),interfaceCode);
  List<RpAccountCheckMistakeScratchPool> platScreatchRecordList=rpAccountCheckMistakeScratchPoolService.listScratchPoolRecord(null);
  List<RpAccountCheckMistake> mistakeList=new ArrayList<RpAccountCheckMistake>();
  List<RpAccountCheckMistakeScratchPool> insertScreatchRecordList=new ArrayList<RpAccountCheckMistakeScratchPool>();
  List<RpAccountCheckMistakeScratchPool> removeScreatchRecordList=new ArrayList<RpAccountCheckMistakeScratchPool>();
  LOG.info("  ????????????,?????????");
  baseOnPaltForm(platSucessDateList,bankList,mistakeList,insertScreatchRecordList,batch);
  LOG.info("????????????");
  LOG.info("  ??????????????");
  baseOnBank(platAllDateList,bankList,platScreatchRecordList,mistakeList,batch,removeScreatchRecordList);
  LOG.info(" ??????????????");
  rpAccountCheckTransactionService.saveDatasaveDate(batch,mistakeList,insertScreatchRecordList,removeScreatchRecordList);
}
