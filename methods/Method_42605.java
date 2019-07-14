/** 
 * ??????
 * @param rpTradePaymentRecord
 */
@Transactional(rollbackFor=Exception.class) void completeSuccessOrder(RpTradePaymentRecord rpTradePaymentRecord,String bankTrxNo,Date timeEnd,String bankReturnMsg){
  LOG.info("??????!");
  rpTradePaymentRecord.setPaySuccessTime(timeEnd);
  rpTradePaymentRecord.setBankTrxNo(bankTrxNo);
  rpTradePaymentRecord.setBankReturnMsg(bankReturnMsg);
  rpTradePaymentRecord.setStatus(TradeStatusEnum.SUCCESS.name());
  rpTradePaymentRecordDao.update(rpTradePaymentRecord);
  RpTradePaymentOrder rpTradePaymentOrder=rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(rpTradePaymentRecord.getMerchantNo(),rpTradePaymentRecord.getMerchantOrderNo());
  rpTradePaymentOrder.setStatus(TradeStatusEnum.SUCCESS.name());
  rpTradePaymentOrder.setTrxNo(rpTradePaymentRecord.getTrxNo());
  rpTradePaymentOrderDao.update(rpTradePaymentOrder);
  if (FundInfoTypeEnum.PLAT_RECEIVES.name().equals(rpTradePaymentRecord.getFundIntoType())) {
    rpAccountTransactionService.creditToAccount(rpTradePaymentRecord.getMerchantNo(),rpTradePaymentRecord.getOrderAmount().subtract(rpTradePaymentRecord.getPlatIncome()),rpTradePaymentRecord.getBankOrderNo(),rpTradePaymentRecord.getBankTrxNo(),rpTradePaymentRecord.getTrxType(),rpTradePaymentRecord.getRemark());
  }
  if (PayTypeEnum.F2F_PAY.name().equals(rpTradePaymentOrder.getPayTypeCode())) {
    String notifyUrl=getMerchantNotifyUrl(rpTradePaymentRecord,rpTradePaymentOrder,rpTradePaymentRecord.getNotifyUrl(),TradeStatusEnum.SUCCESS);
    rpNotifyService.notifySend(notifyUrl,rpTradePaymentRecord.getMerchantOrderNo(),rpTradePaymentRecord.getMerchantNo());
  }
 else {
    String notifyUrl=getMerchantNotifyUrl(rpTradePaymentRecord,rpTradePaymentOrder,rpTradePaymentRecord.getNotifyUrl(),TradeStatusEnum.SUCCESS);
    rpNotifyService.notifySend(notifyUrl,rpTradePaymentRecord.getMerchantOrderNo(),rpTradePaymentRecord.getMerchantNo());
  }
}
