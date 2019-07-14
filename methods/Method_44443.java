public static CurrencyPair translateKrakenCurrencyPair(String currencyPairIn){
  CurrencyPair pair=assetPairMap.get(currencyPairIn);
  if (pair == null) {
    if (currencyPairIn.length() == 6) {
      Currency base=Currency.getInstance(currencyPairIn.substring(0,3));
      if (base.getCommonlyUsedCurrency() != null) {
        base=base.getCommonlyUsedCurrency();
      }
      Currency counter=Currency.getInstance(currencyPairIn.substring(3,6));
      if (counter.getCommonlyUsedCurrency() != null) {
        counter=counter.getCommonlyUsedCurrency();
      }
      pair=new CurrencyPair(base,counter);
    }
 else     if (currencyPairIn.length() == 7) {
      Currency base=Currency.getInstance(currencyPairIn.substring(0,4));
      if (base.getCommonlyUsedCurrency() != null) {
        base=base.getCommonlyUsedCurrency();
      }
      Currency counter=Currency.getInstance(currencyPairIn.substring(4,7));
      if (counter.getCommonlyUsedCurrency() != null) {
        counter=counter.getCommonlyUsedCurrency();
      }
      pair=new CurrencyPair(base,counter);
    }
  }
  return pair;
}
