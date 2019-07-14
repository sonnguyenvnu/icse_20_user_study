public static int getMaxPriceScale(CurrencyPair currencyPair){
  if (currencyPair.base.equals(Currency.BTC) || currencyPair.base.equals(Currency.LTC)) {
    return 5;
  }
 else {
    return 8;
  }
}
