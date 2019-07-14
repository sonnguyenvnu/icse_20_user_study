/** 
 * Calculates the total cost or proceeds at market price of the specified bid/ask amount.
 * @param orderType Ask or bid.
 * @param amount The amount.
 * @return The market cost/proceeds
 * @throws ExchangeException If there is insufficient liquidity.
 */
public BigDecimal marketCostOrProceeds(OrderType orderType,BigDecimal amount){
  BigDecimal remaining=amount;
  BigDecimal cost=ZERO;
  List<BookLevel> orderbookSide=orderType.equals(BID) ? asks : bids;
  for (  BookOrder order : FluentIterable.from(orderbookSide).transformAndConcat(BookLevel::getOrders)) {
    BigDecimal available=order.getRemainingAmount();
    BigDecimal tradeAmount=remaining.compareTo(available) >= 0 ? available : remaining;
    BigDecimal tradeCost=tradeAmount.multiply(order.getLimitPrice());
    cost=cost.add(tradeCost);
    remaining=remaining.subtract(tradeAmount);
    if (remaining.compareTo(ZERO) == 0)     return cost;
  }
  throw new ExchangeException("Insufficient liquidity in book");
}
