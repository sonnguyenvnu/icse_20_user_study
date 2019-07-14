public static String createHuobiCurrencyPair(CurrencyPair currencyPair){
  String pair=assetPairMapReverse.get(currencyPair);
  if ((pair == null) || (pair.length() == 0)) {
    throw new ExchangeException(String.format("Huobi doesn't support currency pair %s",currencyPair.toString()));
  }
  return pair;
}
