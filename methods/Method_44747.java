public Map<String,PoloniexDepth> getAllPoloniexDepths(int depth) throws IOException {
  String command="returnOrderBook";
  return poloniex.getAllOrderBooks(command,"all",depth);
}
