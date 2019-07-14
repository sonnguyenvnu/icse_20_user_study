public static List<Ticker> adaptTickers(BithumbTickersReturn bithumbTickers){
  return bithumbTickers.getTickers().entrySet().stream().map(tickerEntry -> {
    final CurrencyPair currencyPair=new CurrencyPair(tickerEntry.getKey(),Currency.KRW.getCurrencyCode());
    return adaptTicker(tickerEntry.getValue(),currencyPair);
  }
).collect(Collectors.toList());
}
