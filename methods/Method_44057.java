public static CurrencyPair adaptCurrencyPair(String instrumentName){
  String[] temp=instrumentName.split("-",2);
  return new CurrencyPair(temp[0],temp[1]);
}
