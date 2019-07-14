public static Currency translateHuobiCurrencyCode(String currencyIn){
  Currency currencyOut=assetMap.get(currencyIn);
  if (currencyOut == null) {
    logger.error("Huobi does not support the currency code " + currencyIn);
    return null;
  }
  return currencyOut.getCommonlyUsedCurrency();
}
