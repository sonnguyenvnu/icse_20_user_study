@Override public String placeStopOrder(StopOrder stopOrder) throws IOException {
  return super.placeStopLimitOrder(stopOrder.getOriginalAmount(),stopOrder.getLimitPrice(),stopOrder.getStopPrice(),stopOrder.getCurrencyPair(),BTCTurkAdapters.adaptOrderType(stopOrder.getType())).getId();
}
