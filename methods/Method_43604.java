public CexIOOrderWithTransactions getOrderTransactions(String orderId) throws IOException {
  CexIOOrderTransactionsResponse response=cexIOAuthenticated.getOrderTransactions(signatureCreator,new CexioSingleOrderIdRequest(orderId));
  if (response.getError() != null) {
    throw new ExchangeException(response.getError());
  }
  return response.getData();
}
