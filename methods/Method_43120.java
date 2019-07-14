public BitfinexDepth getBitfinexOrderBook(String pair,Integer limitBids,Integer limitAsks) throws IOException {
  BitfinexDepth bitfinexDepth;
  if (limitBids == null && limitAsks == null) {
    bitfinexDepth=bitfinex.getBook(pair);
  }
 else {
    bitfinexDepth=bitfinex.getBook(pair,limitBids,limitAsks);
  }
  return bitfinexDepth;
}
