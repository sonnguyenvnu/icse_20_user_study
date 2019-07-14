public List<BleutradeMarket> getBleutradeMarkets() throws IOException {
  BleutradeMarketsReturn response=bleutrade.getBleutradeMarkets();
  if (!response.getSuccess()) {
    throw new ExchangeException(response.getMessage());
  }
  return response.getResult();
}
