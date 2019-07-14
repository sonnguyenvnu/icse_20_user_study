/** 
 * Given a new LimitOrder, it will replace a matching limit order in the orderbook if one is found, or add the new LimitOrder if one is not. timeStamp will be updated if the new timestamp is non-null and in the future.
 * @param limitOrder the new LimitOrder
 */
public void update(LimitOrder limitOrder){
  update(getOrders(limitOrder.getType()),limitOrder);
  updateDate(limitOrder.getTimestamp());
}
