@Override public Transaction authorize(MerchantStore store,Customer customer,List<ShoppingCartItem> items,BigDecimal amount,Payment payment,IntegrationConfiguration configuration,IntegrationModule module) throws IntegrationException {
  com.salesmanager.core.model.payments.PaypalPayment paypalPayment=(com.salesmanager.core.model.payments.PaypalPayment)payment;
  Validate.notNull(paypalPayment.getPaymentToken(),"A paypal payment token is required to process this transaction");
  return processTransaction(store,customer,items,amount,paypalPayment,configuration,module);
}
