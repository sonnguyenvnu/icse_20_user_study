private UserTrades getTradeHistory(Long from,Long to) throws IOException {
  ANXTradeResultWrapper rawTrades=getExecutedANXTrades(from,to);
  String error=rawTrades.getError();
  if (error != null) {
    throw new IllegalStateException(error);
  }
  return ANXAdapters.adaptUserTrades(rawTrades.getAnxTradeResults(),((ANXExchange)exchange).getANXMetaData());
}
