/** 
 * ????
 * @param mistake ????
 */
@Transactional(rollbackFor=Exception.class) public void handle(String id,String handleType,String handleRemark){
  RpAccountCheckMistake mistake=rpAccountCheckMistakeService.getDataById(id);
  mistake.setHandleStatus(MistakeHandleStatusEnum.HANDLED.name());
  mistake.setHandleRemark(handleRemark);
  rpAccountCheckMistakeService.updateData(mistake);
  Boolean bank=false;
  if ("bank".equals(handleType.trim())) {
    mistake.setHandleValue("?????");
    bank=true;
  }
  if (!bank) {
    return;
  }
switch (mistake.getErrType()) {
case "BANK_MISS":
    if (bank) {
      String trxNo=mistake.getTrxNo();
      rpTradeReconciliationService.bankMissOrBankFailBaseBank(trxNo);
    }
  break;
case "PLATFORM_SHORT_STATUS_MISMATCH":
if (bank) {
  String trxNo=mistake.getTrxNo();
  String bankTrxNo=mistake.getBankTrxNo();
  rpTradeReconciliationService.platFailBankSuccess(trxNo,bankTrxNo);
}
break;
case "PLATFORM_SHORT_CASH_MISMATCH":
if (bank) {
rpTradeReconciliationService.handleAmountMistake(mistake,true);
}
break;
case "PLATFORM_OVER_CASH_MISMATCH":
if (bank) {
rpTradeReconciliationService.handleAmountMistake(mistake,false);
}
break;
case "PLATFORM_OVER_STATUS_MISMATCH":
if (bank) {
String trxNo=mistake.getTrxNo();
rpTradeReconciliationService.bankMissOrBankFailBaseBank(trxNo);
}
break;
case "FEE_MISMATCH":
if (bank) {
rpTradeReconciliationService.handleFeeMistake(mistake);
}
break;
case "PLATFORM_MISS":
break;
default :
break;
}
}
