public static List<Ticker> adaptAllTickers(AllTickersResponse allTickersResponse){
  return Arrays.stream(allTickersResponse.getTicker()).map(ticker -> new Ticker.Builder().currencyPair(adaptCurrencyPair(ticker.getSymbol())).bid(ticker.getBuy()).ask(ticker.getSell()).last(ticker.getLast()).high(ticker.getHigh()).low(ticker.getLow()).volume(ticker.getVol()).quoteVolume(ticker.getVolValue()).build()).collect(Collectors.toList());
}
