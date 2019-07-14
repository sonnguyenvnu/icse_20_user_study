@Override public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {
  return BiboxAdapters.adaptUserTrades(getBiboxOrderHistory());
}
