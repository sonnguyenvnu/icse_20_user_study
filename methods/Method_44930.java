private static void addCurrencyMetaData(Currency symbol,Map<Currency,CurrencyMetaData> currencies,WexMetaData wexMetaData){
  if (!currencies.containsKey(symbol)) {
    BigDecimal withdrawalFee=wexMetaData.getCurrencies().get(symbol) == null ? null : wexMetaData.getCurrencies().get(symbol).getWithdrawalFee();
    currencies.put(symbol,new CurrencyMetaData(wexMetaData.amountScale,withdrawalFee));
  }
}
