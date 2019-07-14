public BitmexTickerList getTicker(String symbol) throws ExchangeException {
  return updateRateLimit(() -> bitmex.getTicker(symbol));
}
