@Override public boolean cancelOrder(String orderId) throws ExchangeException {
  List<BitmexPrivateOrder> orders=cancelBitmexOrder(orderId);
  if (orders.isEmpty()) {
    return true;
  }
  return orders.get(0).getId().equals(orderId);
}
