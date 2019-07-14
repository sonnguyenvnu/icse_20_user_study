private void recordFill(Fill fill){
  if (!fill.isTaker()) {
    publicTrades.push(fill.getTrade());
    if (publicTrades.size() > TRADE_HISTORY_SIZE) {
      publicTrades.removeLast();
    }
  }
  userTrades.put(fill.getApiKey(),fill.getTrade());
  accountFactory.get(fill.getApiKey()).fill(fill.getTrade(),!fill.isTaker());
  onFill.accept(fill);
}
