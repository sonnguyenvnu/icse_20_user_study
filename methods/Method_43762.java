public List<CoindirectMarket> getCoindirectMarkets(long max) throws IOException {
  return coindirect.listExchangeMarkets(max);
}
