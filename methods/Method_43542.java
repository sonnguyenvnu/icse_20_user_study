public CampBXResponse placeCampBXMarketOrder(MarketOrder marketOrder) throws IOException {
  CampBX.AdvTradeMode mode=marketOrder.getType() == Order.OrderType.ASK ? CampBX.AdvTradeMode.AdvancedSell : CampBX.AdvTradeMode.AdvancedBuy;
  CampBXResponse campBXResponse=campBX.tradeAdvancedMarketEnter(exchange.getExchangeSpecification().getUserName(),exchange.getExchangeSpecification().getPassword(),mode,marketOrder.getOriginalAmount(),CampBX.MarketPrice.Market,null,null,null);
  return campBXResponse;
}
