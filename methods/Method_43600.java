public CexIOCancelAllOrdersResponse cancelCexIOOrders(CurrencyPair currencyPair) throws IOException {
  return cexIOAuthenticated.cancelAllOrders(signatureCreator,currencyPair.base.getCurrencyCode(),currencyPair.counter.getCurrencyCode(),new CexIORequest());
}
