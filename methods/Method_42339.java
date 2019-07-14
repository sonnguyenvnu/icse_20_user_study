/** 
 * ??????????
 * @param bankList ???????
 * @param misTakeList ??list
 * @param platScreatchRecordList ?????????
 * @param batch ????
 */
private void baseOnBank(List<RpTradePaymentRecord> platAllDateList,List<ReconciliationEntityVo> bankList,List<RpAccountCheckMistakeScratchPool> platScreatchRecordList,List<RpAccountCheckMistake> misTakeList,RpAccountCheckBatch batch,List<RpAccountCheckMistakeScratchPool> removeScreatchRecordList){
  BigDecimal platTradeAmount=BigDecimal.ZERO;
  BigDecimal platFee=BigDecimal.ZERO;
  Integer tradeCount=0;
  Integer mistakeCount=0;
  for (  ReconciliationEntityVo bankRecord : bankList) {
    boolean flag=false;
    for (    RpTradePaymentRecord record : platAllDateList) {
      if (bankRecord.getBankOrderNo().equals(record.getBankOrderNo())) {
        flag=true;
        if (!TradeStatusEnum.SUCCESS.name().equals(record.getStatus())) {
          RpAccountCheckMistake misktake1=createMisktake(null,record,bankRecord,ReconciliationMistakeTypeEnum.PLATFORM_SHORT_STATUS_MISMATCH,batch);
          misTakeList.add(misktake1);
          mistakeCount++;
          if (record.getOrderAmount().compareTo(bankRecord.getBankAmount()) == 1) {
            RpAccountCheckMistake misktake=createMisktake(null,record,bankRecord,ReconciliationMistakeTypeEnum.PLATFORM_OVER_CASH_MISMATCH,batch);
            misTakeList.add(misktake);
            mistakeCount++;
            break;
          }
 else           if (record.getOrderAmount().compareTo(bankRecord.getBankAmount()) == -1) {
            RpAccountCheckMistake misktake=createMisktake(null,record,bankRecord,ReconciliationMistakeTypeEnum.PLATFORM_SHORT_CASH_MISMATCH,batch);
            misTakeList.add(misktake);
            mistakeCount++;
            break;
          }
          if (record.getPlatCost().compareTo(bankRecord.getBankFee()) != 0) {
            RpAccountCheckMistake misktake=createMisktake(null,record,bankRecord,ReconciliationMistakeTypeEnum.FEE_MISMATCH,batch);
            misTakeList.add(misktake);
            mistakeCount++;
            break;
          }
        }
      }
    }
    if (!flag) {
      if (platScreatchRecordList != null)       for (      RpAccountCheckMistakeScratchPool scratchRecord : platScreatchRecordList) {
        if (scratchRecord.getBankOrderNo().equals(bankRecord.getBankOrderNo())) {
          platTradeAmount=platTradeAmount.add(scratchRecord.getOrderAmount());
          platFee=platFee.add(scratchRecord.getPlatCost() == null ? BigDecimal.ZERO : scratchRecord.getPlatCost());
          tradeCount++;
          flag=true;
          if (scratchRecord.getOrderAmount().compareTo(bankRecord.getBankAmount()) == 1) {
            RpAccountCheckMistake misktake=createMisktake(scratchRecord,null,bankRecord,ReconciliationMistakeTypeEnum.PLATFORM_OVER_CASH_MISMATCH,batch);
            misTakeList.add(misktake);
            mistakeCount++;
            break;
          }
 else           if (scratchRecord.getOrderAmount().compareTo(bankRecord.getBankAmount()) == -1) {
            RpAccountCheckMistake misktake=createMisktake(scratchRecord,null,bankRecord,ReconciliationMistakeTypeEnum.PLATFORM_SHORT_CASH_MISMATCH,batch);
            misTakeList.add(misktake);
            mistakeCount++;
            break;
          }
          if (scratchRecord.getPlatCost().compareTo(bankRecord.getBankFee()) != 0) {
            RpAccountCheckMistake misktake=createMisktake(scratchRecord,null,bankRecord,ReconciliationMistakeTypeEnum.FEE_MISMATCH,batch);
            misTakeList.add(misktake);
            mistakeCount++;
            break;
          }
          removeScreatchRecordList.add(scratchRecord);
        }
      }
    }
    if (!flag) {
      RpAccountCheckMistake misktake=createMisktake(null,null,bankRecord,ReconciliationMistakeTypeEnum.PLATFORM_MISS,batch);
      misTakeList.add(misktake);
      mistakeCount++;
    }
  }
  batch.setTradeAmount(batch.getTradeAmount().add(platTradeAmount));
  batch.setTradeCount(batch.getTradeCount() + tradeCount);
  batch.setFee(batch.getFee().add(platFee));
  batch.setMistakeCount(batch.getMistakeCount() + mistakeCount);
}
