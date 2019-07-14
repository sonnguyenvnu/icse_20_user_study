public boolean addAll(CoinbaseProTrades coinbaseProTrades){
  if (earliestTradeId == null) {
    earliestTradeId=coinbaseProTrades.getEarliestTradeId();
  }
 else   if (coinbaseProTrades.getEarliestTradeId() != null) {
    earliestTradeId=Math.min(earliestTradeId,coinbaseProTrades.getEarliestTradeId());
  }
  if (latestTradeId == null) {
    latestTradeId=coinbaseProTrades.getLatestTradeId();
  }
 else   if (coinbaseProTrades.getLatestTradeId() != null) {
    latestTradeId=Math.max(latestTradeId,coinbaseProTrades.getLatestTradeId());
  }
  return super.addAll(coinbaseProTrades);
}
