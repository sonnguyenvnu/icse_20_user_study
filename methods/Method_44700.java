@Override public List<Ticker> getTickers(Params params) throws IOException {
  return okex.getAllTokenPairInformations().stream().map(OkexAdaptersV3::convert).collect(Collectors.toList());
}
