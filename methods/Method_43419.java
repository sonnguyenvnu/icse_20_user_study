@Override public TradeHistoryParams createFundingHistoryParams(){
  return new Bl3pTradeHistoryParams(Currency.BTC,Bl3pTradeHistoryParams.TransactionType.DEPOSIT);
}
