public CoinbeneTicker.Container getCoinbeneTickers() throws IOException {
  try {
    return checkSuccess(coinbene.ticker("all"));
  }
 catch (  CoinbeneException e) {
    throw new ExchangeException(e.getMessage(),e);
  }
}
