public CoinfloorOrder placeLimitOrder(CurrencyPair pair,OrderType side,BigDecimal amount,BigDecimal price) throws IOException {
  Currency base=normalise(pair.base);
  Currency counter=normalise(pair.counter);
  try {
    if (side == OrderType.BID) {
      return coinfloor.buy(base,counter,amount,price);
    }
 else {
      return coinfloor.sell(base,counter,amount,price);
    }
  }
 catch (  HttpStatusIOException e) {
    if (e.getHttpStatusCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
      throw new ExchangeException(e.getHttpBody(),e);
    }
 else {
      throw e;
    }
  }
}
