public static String getBaseCurrency(@Nullable CurrencyPair currencyPair){
  return Optional.ofNullable(currencyPair).map(c -> c.base).map(Currency::getCurrencyCode).orElse(null);
}
