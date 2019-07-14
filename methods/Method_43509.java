private static UserTrade adaptUserTrade(List<BxTradeHistory> histories){
  UserTrade trade=null;
  int indexOfFirstTrade=-1;
  int indexOfSecondTrade=-1;
  int indexOfFee=-1;
  for (int i=0; i < histories.size(); i++) {
    if (histories.get(i).getType().equals(TRADE)) {
      if (indexOfFirstTrade < 0) {
        indexOfFirstTrade=i;
      }
 else       if (indexOfSecondTrade < 0) {
        indexOfSecondTrade=i;
      }
    }
 else     if ((histories.get(i).getType().equals(FEE)) && (indexOfFee < 0)) {
      indexOfFee=i;
    }
  }
  if ((indexOfFirstTrade > -1) && (indexOfSecondTrade > -1)) {
    if (!histories.get(indexOfSecondTrade).getAmount().equals(BigDecimal.ZERO)) {
      BxTradeHistory history=histories.get(indexOfSecondTrade);
      trade=new UserTrade((history.getAmount().compareTo(BigDecimal.ZERO) > 0) ? OrderType.BID : OrderType.ASK,history.getAmount().abs(),new CurrencyPair(history.getCurrency(),histories.get(indexOfFirstTrade).getCurrency()),histories.get(indexOfFirstTrade).getAmount().divide(history.getAmount(),6,BigDecimal.ROUND_HALF_UP).abs(),adaptDate(history.getDate()),String.valueOf(history.getTransactionId()),String.valueOf(history.getRefId()),(indexOfFee < 0) ? null : histories.get(indexOfFee).getAmount().abs(),(indexOfFee < 0) ? null : BxUtils.translateCurrency(histories.get(indexOfFee).getCurrency()));
    }
  }
  return trade;
}
