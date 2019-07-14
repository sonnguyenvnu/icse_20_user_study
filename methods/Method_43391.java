public BitZKline getKline(CurrencyPair currencyPair,String timescale) throws IOException {
  return this.getBitZKline(BitZUtils.toPairString(currencyPair),timescale);
}
