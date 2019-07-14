/** 
 * ??????
 * @param rpTradePaymentRecord
 */
private void completeFailOrder(RpTradePaymentRecord rpTradePaymentRecord,String bankReturnMsg){
  rpTradePaymentRecord.setBankReturnMsg(bankReturnMsg);
  rpTradePaymentRecord.setStatus(TradeStatusEnum.FAILED.name());
  rpTradePaymentRecordDao.update(rpTradePaymentRecord);
  RpTradePaymentOrder rpTradePaymentOrder=rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(rpTradePaymentRecord.getMerchantNo(),rpTradePaymentRecord.getMerchantOrderNo());
  rpTradePaymentOrder.setStatus(TradeStatusEnum.FAILED.name());
  rpTradePaymentOrderDao.update(rpTradePaymentOrder);
  String notifyUrl=getMerchantNotifyUrl(rpTradePaymentRecord,rpTradePaymentOrder,rpTradePaymentRecord.getNotifyUrl(),TradeStatusEnum.FAILED);
  rpNotifyService.notifySend(notifyUrl,rpTradePaymentRecord.getMerchantOrderNo(),rpTradePaymentRecord.getMerchantNo());
}
