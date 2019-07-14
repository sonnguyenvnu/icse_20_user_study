public ExchangeMetaData getMetadata() throws IOException {
  List<CoinbeneSymbol> symbol=getSymbol().getSymbol();
  return CoinbeneAdapters.adaptMetadata(symbol);
}
