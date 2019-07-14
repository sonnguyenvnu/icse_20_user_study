public CexIOOpenOrder getOrderDetail(String orderId) throws IOException {
  return cexIOAuthenticated.getOrder(signatureCreator,new CexioSingleOrderIdRequest(orderId));
}
