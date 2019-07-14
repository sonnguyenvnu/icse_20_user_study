public ItBitOrder[] getItBitOrderHistory(String currency,String pageNum,String pageLen) throws IOException {
  ItBitOrder[] orders=itBitAuthenticated.getOrders(signatureCreator,new Date().getTime(),exchange.getNonceFactory(),currency,pageNum,pageLen,"filled",walletId);
  return orders;
}
