@Override public Transaction authorize(MerchantStore store,Customer customer,List<ShoppingCartItem> items,BigDecimal amount,Payment payment,IntegrationConfiguration configuration,IntegrationModule module) throws IntegrationException {
  return processTransaction(store,customer,TransactionType.AUTHORIZE,amount,payment,configuration,module);
}
