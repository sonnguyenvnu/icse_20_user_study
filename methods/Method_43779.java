@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  BigDecimal amount=limitOrder.getOriginalAmount();
  BigDecimal price=limitOrder.getAveragePrice();
  String type=limitOrder.getType() == OrderType.ASK ? "buy" : "sell";
  String coin=limitOrder.getCurrencyPair().base.getCurrencyCode().toLowerCase();
  return CoinEggAdapters.adaptTradeAdd(getCoinEggTradeAdd(amount,price,type,coin));
}
