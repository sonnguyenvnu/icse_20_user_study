public Map<String,LiquiDepth> getAllDepths(){
  return checkResult(liqui.getDepth(new Liqui.Pairs(new ArrayList<>(exchange.getExchangeMetaData().getCurrencyPairs().keySet()))));
}
