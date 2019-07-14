/** 
 * ??????
 * @param scratchRecord ???????????
 * @param record ??????
 * @param bankRecord ??????
 * @param mistakeType ????
 * @return ???scratchRecord?record ???????
 */
private RpAccountCheckMistake createMisktake(RpAccountCheckMistakeScratchPool scratchRecord,RpTradePaymentRecord record,ReconciliationEntityVo bankRecord,ReconciliationMistakeTypeEnum mistakeType,RpAccountCheckBatch batch){
  RpAccountCheckMistake mistake=new RpAccountCheckMistake();
  mistake.setAccountCheckBatchNo(batch.getBatchNo());
  mistake.setBillDate(batch.getBillDate());
  mistake.setErrType(mistakeType.name());
  mistake.setHandleStatus(MistakeHandleStatusEnum.NOHANDLE.name());
  mistake.setBankType(batch.getBankType());
  if (record != null) {
    mistake.setMerchantName(record.getMerchantName());
    mistake.setMerchantNo(record.getMerchantNo());
    mistake.setOrderNo(record.getMerchantOrderNo());
    mistake.setTradeTime(record.getPaySuccessTime());
    mistake.setTrxNo(record.getTrxNo());
    mistake.setOrderAmount(record.getOrderAmount());
    mistake.setRefundAmount(record.getSuccessRefundAmount());
    mistake.setTradeStatus(record.getStatus());
    mistake.setFee(record.getPlatCost());
  }
  if (scratchRecord != null) {
    mistake.setOrderNo(scratchRecord.getMerchantOrderNo());
    mistake.setTradeTime(scratchRecord.getPaySuccessTime());
    mistake.setTrxNo(scratchRecord.getTrxNo());
    mistake.setOrderAmount(scratchRecord.getOrderAmount());
    mistake.setRefundAmount(scratchRecord.getSuccessRefundAmount());
    mistake.setTradeStatus(scratchRecord.getStatus());
    mistake.setFee(scratchRecord.getPlatCost());
  }
  if (bankRecord != null) {
    mistake.setBankAmount(bankRecord.getBankAmount());
    mistake.setBankFee(bankRecord.getBankFee());
    mistake.setBankOrderNo(bankRecord.getBankOrderNo());
    mistake.setBankRefundAmount(bankRecord.getBankRefundAmount());
    mistake.setBankTradeStatus(bankRecord.getBankTradeStatus());
    mistake.setBankTradeTime(bankRecord.getBankTradeTime());
    mistake.setBankTrxNo(bankRecord.getBankTrxNo());
  }
  return mistake;
}
