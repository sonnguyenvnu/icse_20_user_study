/** 
 * Cancel a batch of up to three orders (maximum allowed by the exchange).
 * @param limitOrders orders to cancel
 * @return (id, result) mappings
 */
public Map<LimitOrder,Boolean> cancelUpToThreeOrders(List<LimitOrder> limitOrders) throws IOException {
  Set<Long> ordersToCancel=limitOrders.stream().map(Order::getId).map(Long::parseLong).collect(Collectors.toSet());
  if (ordersToCancel.isEmpty() || ordersToCancel.size() > 3) {
    throw new UnsupportedOperationException("Can only batch cancel 1 to 3 orders. " + ordersToCancel.size() + " orders provided.");
  }
  CurrencyPair currencyPair=limitOrders.get(0).getCurrencyPair();
  boolean valid=limitOrders.stream().allMatch(order -> order.getCurrencyPair().equals(currencyPair));
  if (!valid) {
    throw new UnsupportedOperationException("Can only batch cancel orders with the same currency pair.");
  }
  OkCoinBatchTradeResult okCoinBatchTradeResult=cancelUpToThreeOrders(ordersToCancel,OkCoinAdapters.adaptSymbol(currencyPair));
  Map<String,Boolean> requestResults=new HashMap<>(ordersToCancel.size());
  if (okCoinBatchTradeResult.getSuccess() != null) {
    Arrays.stream(okCoinBatchTradeResult.getSuccess().split(BATCH_DELIMITER)).forEach(id -> requestResults.put(id,Boolean.TRUE));
  }
  if (okCoinBatchTradeResult.getError() != null) {
    Arrays.stream(okCoinBatchTradeResult.getError().split(BATCH_DELIMITER)).forEach(id -> requestResults.put(id,Boolean.FALSE));
  }
  Map<LimitOrder,Boolean> results=new HashMap<>(limitOrders.size());
  requestResults.forEach((id,result) -> limitOrders.stream().filter(order -> order.getId().equals(id)).findAny().ifPresent(limitOrder -> results.put(limitOrder,requestResults.get(id))));
  return results;
}
