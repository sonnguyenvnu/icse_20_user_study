public ExchangeMetaData getMetadata() throws IOException {
  List<BiboxMarket> markets=getAllBiboxMarkets();
  return BiboxAdapters.adaptMetadata(markets);
}
