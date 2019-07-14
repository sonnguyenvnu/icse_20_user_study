public List<BittrexSymbol> getBittrexSymbols() throws IOException {
  return bittrexAuthenticated.getSymbols().getResult();
}
