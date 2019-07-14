public static CurrencyPair adaptCcyPair(String instrument){
  Currency base=adaptCcy(instrument.substring(0,3));
  Currency counter=adaptCcy(instrument.substring(3,6));
  return new CurrencyPair(base,counter);
}
