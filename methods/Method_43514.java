public static String createBxCurrencyPair(CurrencyPair currencyPair){
  String pair=assetPairMapReverse.get(currencyPair);
  if ((pair == null) || pair.isEmpty()) {
    throw new ExchangeException(String.format("BX doesn't support currency pair %s",currencyPair.toString()));
  }
  return pair;
}
