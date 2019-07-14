public static Currency fromLunoCurrency(String c){
  String in;
switch (c) {
case "XBT":
    in="BTC";
  break;
default :
in=c;
}
return Currency.getInstance(in);
}
