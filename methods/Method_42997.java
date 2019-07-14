@Override public List<Ticker> getTickers(Params params) throws IOException {
  try {
    return ticker24h().stream().map(BinanceTicker24h::toTicker).collect(Collectors.toList());
  }
 catch (  BinanceException e) {
    throw BinanceErrorAdapter.adapt(e);
  }
}
