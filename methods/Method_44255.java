public GateioDepth getBTEROrderBook(String tradeableIdentifier,String currency) throws IOException {
  GateioDepth gateioDepth=bter.getFullDepth(tradeableIdentifier.toLowerCase(),currency.toLowerCase());
  return handleResponse(gateioDepth);
}
