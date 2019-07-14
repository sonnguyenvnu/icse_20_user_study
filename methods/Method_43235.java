public CurrencyPair determineActiveContract(String baseSymbol,String counterSymbol,BitmexPrompt contractTimeframe){
  if (baseSymbol.equals("BTC")) {
    baseSymbol="XBT";
  }
  if (counterSymbol.equals("BTC")) {
    counterSymbol="XBT";
  }
  final String symbols=baseSymbol + "/" + counterSymbol;
  BitmexTickerList tickerList=((BitmexMarketDataServiceRaw)marketDataService).getTicker(baseSymbol + ":" + contractTimeframe);
  String bitmexSymbol=tickerList.stream().map(BitmexTicker::getSymbol).findFirst().orElseThrow(() -> new ExchangeException("Instrument for " + symbols + " " + contractTimeframe + " is not active or does not exist"));
  String contractTypeSymbol=bitmexSymbol.substring(3,bitmexSymbol.length());
  return new CurrencyPair(baseSymbol,contractTypeSymbol);
}
