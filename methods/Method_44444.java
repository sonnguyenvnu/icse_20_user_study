public static Currency translateKrakenCurrencyCode(String currencyIn){
  Currency currencyOut=assetsMap.get(currencyIn);
  if (currencyOut == null) {
    throw new ExchangeException("Kraken does not support the currency code " + currencyIn);
  }
  return currencyOut.getCommonlyUsedCurrency();
}
