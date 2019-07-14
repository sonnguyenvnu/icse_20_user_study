private BigDecimal getPriceForMarketOrder(List<DVChainLevel> levels,MarketOrder marketOrder){
  BigDecimal quantity=marketOrder.getOriginalAmount();
  for (  DVChainLevel level : levels) {
    if (quantity.compareTo(level.getMaxQuantity()) <= 0) {
      return marketOrder.getType() == Order.OrderType.BID ? level.getBuyPrice() : level.getSellPrice();
    }
  }
  throw new FundsExceededException();
}
