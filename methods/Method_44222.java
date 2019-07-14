private String currencyPairFormat(CurrencyPair currencyPair){
  String currency=currencyPair.toString();
  currency=currency.replace("/","_");
  return currency;
}
