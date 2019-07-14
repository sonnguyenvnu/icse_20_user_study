@Override public TradeHistoryParams createFundingHistoryParams(){
  return new BitstampTradeHistoryParams(null,BitstampUtils.MAX_TRANSACTIONS_PER_QUERY);
}
