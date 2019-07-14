public List<CCEXMarket> getConbaseExProducts() throws IOException {
  CCEXMarkets cCEXTrades=ccex.getProducts();
  return cCEXTrades.getResult();
}
