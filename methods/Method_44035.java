@Override public TradeHistoryParams createFundingHistoryParams(){
  return new CryptonitTradeHistoryParams(null,CryptonitUtils.MAX_TRANSACTIONS_PER_QUERY);
}
