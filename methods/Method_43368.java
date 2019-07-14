public BittrexDepth getBittrexOrderBook(String pair,int depth) throws IOException {
  return bittrexAuthenticated.getBook(pair,"both",depth).getResult();
}
