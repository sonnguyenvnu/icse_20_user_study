private String formatCurrencyPair(CurrencyPair currencyPair){
  return String.format("%s_%s",currencyPair.base.getCurrencyCode(),currencyPair.counter.getCurrencyCode()).toLowerCase();
}
