private static void addCurrencyMetaData(Currency symbol,Map<Currency,CurrencyMetaData> currencies,DSXMetaData dsxMetaData){
  if (!currencies.containsKey(symbol)) {
    BigDecimal withdrawalFee=dsxMetaData.getCurrencies().get(symbol) == null ? null : dsxMetaData.getCurrencies().get(symbol).getWithdrawalFee();
    currencies.put(symbol,new CurrencyMetaData(dsxMetaData.amountScale,withdrawalFee));
  }
}
