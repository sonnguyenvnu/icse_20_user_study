public BitfinexLend[] getBitfinexLends(String currency,long sinceTimestamp,int limitTrades) throws IOException {
  BitfinexLend[] bitfinexLends=bitfinex.getLends(currency,sinceTimestamp,limitTrades);
  return bitfinexLends;
}
