private static void raw(BitfinexMarketDataServiceRaw marketDataService) throws IOException {
  Collection<String> symbols=marketDataService.getBitfinexSymbols();
  System.out.println(symbols);
  Collection<CurrencyPair> currencyPairs=marketDataService.getExchangeSymbols();
  System.out.println(currencyPairs);
}
