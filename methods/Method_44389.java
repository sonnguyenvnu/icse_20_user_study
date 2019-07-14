public IndependentReserveTicker getIndependentReserveTicker(String baseSymbol,String counterSymbol) throws IOException {
  return independentReserve.getMarketSummary(baseSymbol,counterSymbol);
}
