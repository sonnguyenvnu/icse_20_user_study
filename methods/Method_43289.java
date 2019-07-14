/** 
 * See  {@link Bitmex#getOrders}
 * @return List of {@link BitmexPrivateOrder}s.
 */
public List<BitmexPrivateOrder> getBitmexOrders(@Nullable String symbol,@Nullable String filter,@Nullable String columns,@Nullable Date startTime,@Nullable Date endTime) throws ExchangeException {
  ArrayList<BitmexPrivateOrder> orders=new ArrayList<>();
  for (int i=0; orders.size() % 500 == 0; i++) {
    final int j=i;
    List<BitmexPrivateOrder> orderResponse=updateRateLimit(() -> bitmex.getOrders(apiKey,exchange.getNonceFactory(),signatureCreator,symbol,filter,columns,500,(long)(j * 500),true,startTime,endTime));
    orders.addAll(orderResponse);
    if (orderResponse.size() == 0)     break;
  }
  return orders;
}
