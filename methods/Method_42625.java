/** 
 * ???????????.
 * @param trxNo ??????
 * @param bankTrxNo ??????
 */
@Transactional(rollbackFor=Exception.class) public void platFailBankSuccess(String trxNo,String bankTrxNo){
  LOG.info("===== ???????????.========");
  RpTradePaymentRecord record=rpTradePaymentRecordDao.getByTrxNo(trxNo);
  if (record == null) {
    throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR,"trxNo[" + trxNo + "]????????");
  }
  record.setBankTrxNo(bankTrxNo);
  record.setBankReturnMsg("SUCCESS");
  record.setStatus(TradeStatusEnum.SUCCESS.name());
  rpTradePaymentRecordDao.update(record);
  RpTradePaymentOrder rpTradePaymentOrder=rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(record.getMerchantNo(),record.getMerchantOrderNo());
  rpTradePaymentOrder.setStatus(TradeStatusEnum.SUCCESS.name());
  rpTradePaymentOrderDao.update(rpTradePaymentOrder);
  rpAccountTransactionService.creditToAccount(record.getMerchantNo(),record.getOrderAmount().subtract(record.getPlatIncome()),record.getBankOrderNo(),record.getBankTrxNo(),record.getTrxType(),record.getRemark());
  rpNotifyService.notifySend(record.getNotifyUrl(),record.getMerchantOrderNo(),record.getMerchantNo());
}
