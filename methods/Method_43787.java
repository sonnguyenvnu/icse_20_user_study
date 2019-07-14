public boolean hasCurrency(Currency currency){
  if (currency.equals(Currency.BTC)) {
    return !Objects.equals(btcBalance,BigDecimal.ZERO);
  }
 else   if (currency.equals(Currency.GBP)) {
    return !Objects.equals(gbpBalance,BigDecimal.ZERO);
  }
 else   if (currency.equals(Currency.EUR)) {
    return !Objects.equals(eurBalance,BigDecimal.ZERO);
  }
 else   if (currency.equals(Currency.USD)) {
    return !Objects.equals(usdBalance,BigDecimal.ZERO);
  }
 else   if (currency.equals(Currency.BCH)) {
    return !Objects.equals(bchBalance,BigDecimal.ZERO);
  }
 else   if (currency.equals(Currency.XRP)) {
    return !Objects.equals(xrpBalance,BigDecimal.ZERO);
  }
 else   if (currency.equals(Currency.LTC)) {
    return !Objects.equals(ltcBalance,BigDecimal.ZERO);
  }
 else   if (currency.equals(Currency.ETH)) {
    return !Objects.equals(ethBalance,BigDecimal.ZERO);
  }
 else {
    return false;
  }
}
