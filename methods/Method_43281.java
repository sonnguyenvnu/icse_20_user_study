public BitmexTickerList getActiveTickers() throws ExchangeException {
  return updateRateLimit(() -> bitmex.getActiveTickers());
}
