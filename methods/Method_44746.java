public Map<String,PoloniexMarketData> getAllPoloniexTickers() throws IOException {
  String command="returnTicker";
  return poloniex.getTicker(command);
}
