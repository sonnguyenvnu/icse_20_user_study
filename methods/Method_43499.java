public List<BTCTurkOHLC> getBTCTurkOHLC(CurrencyPair pair,Integer last) throws IOException {
  return btcTurk.getOHLC(pair.toString().replace("/",""),last);
}
