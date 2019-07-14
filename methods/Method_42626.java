/** 
 * ?????????
 * @param mistake ????
 * @param isBankMore ????????
 * @param baseOnBank ???????
 */
@Transactional(rollbackFor=Exception.class) public void handleAmountMistake(RpAccountCheckMistake mistake,boolean isBankMore){
  LOG.info("=====????????,????????[" + isBankMore + "],??????????========");
  String trxNo=mistake.getTrxNo();
  RpTradePaymentRecord record=rpTradePaymentRecordDao.getByTrxNo(trxNo);
  if (record == null) {
    throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR,"trxNo[" + trxNo + "]????????");
  }
  if (!record.getStatus().equals(TradeStatusEnum.SUCCESS.name())) {
    throw new TradeBizException(TradeBizException.TRADE_ORDER_STATUS_NOT_SUCCESS,"??????????????");
  }
  BigDecimal bankAmount=mistake.getBankAmount();
  BigDecimal bankFee=mistake.getBankFee();
  BigDecimal orderAmount=record.getOrderAmount();
  BigDecimal fee=record.getPlatIncome();
  BigDecimal needFee=bankAmount.multiply(record.getFeeRate()).divide(BigDecimal.valueOf(100),2,BigDecimal.ROUND_HALF_UP);
  BigDecimal subOrderAmount=bankAmount.subtract(orderAmount).abs();
  BigDecimal subFee=needFee.subtract(fee).abs();
  if (isBankMore) {
    record.setOrderAmount(bankAmount);
    record.setPlatCost(bankFee);
    record.setPlatIncome(needFee);
    record.setRemark("??????????[" + subOrderAmount + "],????[" + subFee + "],????[" + bankFee + "]");
    rpTradePaymentRecordDao.update(record);
    RpTradePaymentOrder rpTradePaymentOrder=rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(record.getMerchantNo(),record.getMerchantOrderNo());
    rpTradePaymentOrder.setOrderAmount(bankAmount);
    rpTradePaymentOrder.setRemark("????:?????[" + orderAmount + "]??[" + bankAmount + "]");
    rpTradePaymentOrderDao.update(rpTradePaymentOrder);
    rpAccountTransactionService.creditToAccount(record.getMerchantNo(),subOrderAmount.subtract(subFee),record.getBankOrderNo(),record.getBankTrxNo(),TrxTypeEnum.ERRORHANKLE.name(),"???????");
  }
 else {
    record.setOrderAmount(bankAmount);
    record.setPlatCost(bankFee);
    record.setPlatIncome(needFee);
    record.setRemark("??????????[" + subOrderAmount + "],????[" + subFee + "],????[" + bankFee + "]");
    rpTradePaymentRecordDao.update(record);
    RpTradePaymentOrder rpTradePaymentOrder=rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(record.getMerchantNo(),record.getMerchantOrderNo());
    rpTradePaymentOrder.setOrderAmount(bankAmount);
    rpTradePaymentOrder.setRemark("????:?????[" + orderAmount + "]??[" + bankAmount + "]");
    rpTradePaymentOrderDao.update(rpTradePaymentOrder);
    rpAccountTransactionService.debitToAccount(record.getMerchantNo(),subOrderAmount.subtract(subFee),record.getBankOrderNo(),record.getBankTrxNo(),TrxTypeEnum.ERRORHANKLE.name(),"???????");
  }
}
