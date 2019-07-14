public BTCTradeTrade[] getBTCTradeTrades(long since) throws IOException {
  return btcTrade.getTrades(since);
}
