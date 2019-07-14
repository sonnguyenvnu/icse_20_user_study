@Override public List<Ticker> getTickers(Params params) throws IOException {
  return BTCTurkAdapters.adaptTicker(super.getBTCTurkTicker());
}
