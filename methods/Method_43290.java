/** 
 * See  {@link Bitmex#getOrders}
 * @return List of {@link BitmexPrivateOrder}s.
 */
public List<BitmexPrivateOrder> getBitmexOrders() throws ExchangeException {
  return getBitmexOrders(null,null,null,null,null);
}
