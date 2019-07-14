public static Currency adaptCcy(String ccy){
  if (ccy.toUpperCase().equals("XBT"))   return Currency.BTC;
  return Currency.getInstance(ccy);
}
