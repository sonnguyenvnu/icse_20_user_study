public Map<String,LiquiDepth> getAllDepths(final int limit){
  return checkResult(liqui.getDepth(new Liqui.Pairs(new ArrayList<>(exchange.getExchangeMetaData().getCurrencyPairs().keySet())),limit));
}
