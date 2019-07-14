public static CurrencyPair toPair(String instrument){
  return instrument == null ? null : new CurrencyPair(instrument.replace('-','/'));
}
