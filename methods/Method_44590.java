public static String toLunoCurrency(Currency c){
  String in=c.getCommonlyUsedCurrency().getCurrencyCode();
switch (in) {
case "BTC":
    return "XBT";
default :
  return in;
}
}
