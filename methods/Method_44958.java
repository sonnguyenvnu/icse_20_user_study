public ExchangeMetaData getMetadata() throws IOException {
  List<ZaifMarket> markets=this.getAllMarkets();
  return ZaifAdapters.adaptMetadata(markets);
}
