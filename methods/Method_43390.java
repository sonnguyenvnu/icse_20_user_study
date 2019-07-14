public List<Ticker> getTickers(Object... args) throws IOException {
  return BitZAdapters.adaptTickers(getBitZTickerAll());
}
