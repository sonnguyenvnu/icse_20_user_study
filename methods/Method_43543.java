public CampBXResponse placeCampBXLimitOrder(LimitOrder limitOrder) throws IOException {
  CampBX.TradeMode mode=limitOrder.getType() == Order.OrderType.ASK ? CampBX.TradeMode.QuickSell : CampBX.TradeMode.QuickBuy;
  CampBXResponse campBXResponse=campBX.tradeEnter(exchange.getExchangeSpecification().getUserName(),exchange.getExchangeSpecification().getPassword(),mode,limitOrder.getOriginalAmount(),limitOrder.getLimitPrice());
  return campBXResponse;
}
