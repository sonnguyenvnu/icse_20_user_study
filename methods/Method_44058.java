public static String adaptInstrumentName(CurrencyPair pair){
  return pair.base.getSymbol() + "-" + pair.counter.getSymbol();
}
