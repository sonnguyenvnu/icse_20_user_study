public String getPairId(){
  CurrencyPair pair=getCurrencyPair();
  return pair == null ? null : CobinhoodAdapters.adaptCurrencyPair(pair);
}
