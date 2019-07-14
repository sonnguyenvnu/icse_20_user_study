@Override public OkCoinFuturesTradeHistoryParams createTradeHistoryParams(){
  return new OkCoinFuturesTradeHistoryParams(50,0,CurrencyPair.BTC_USD,futuresContract,null,"2018-08-10");
}
