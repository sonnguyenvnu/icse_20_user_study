@Override public Transaction capture(MerchantStore store,Customer customer,Order order,Transaction capturableTransaction,IntegrationConfiguration configuration,IntegrationModule module) throws IntegrationException {
  Transaction transaction=new Transaction();
  try {
    String apiKey=configuration.getIntegrationKeys().get("secretKey");
    if (StringUtils.isBlank(apiKey)) {
      IntegrationException te=new IntegrationException("Can't process Stripe, missing payment.metaData");
      te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
      te.setMessageCode("message.payment.error");
      te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
      throw te;
    }
    String chargeId=capturableTransaction.getTransactionDetails().get("TRNORDERNUMBER");
    if (StringUtils.isBlank(chargeId)) {
      IntegrationException te=new IntegrationException("Can't process Stripe capture, missing TRNORDERNUMBER");
      te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
      te.setMessageCode("message.payment.error");
      te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
      throw te;
    }
    Stripe.apiKey=apiKey;
    Charge ch=Charge.retrieve(chargeId);
    ch.capture();
    transaction.setAmount(order.getTotal());
    transaction.setOrder(order);
    transaction.setTransactionDate(new Date());
    transaction.setTransactionType(TransactionType.CAPTURE);
    transaction.setPaymentType(PaymentType.CREDITCARD);
    transaction.getTransactionDetails().put("TRANSACTIONID",capturableTransaction.getTransactionDetails().get("TRANSACTIONID"));
    transaction.getTransactionDetails().put("TRNAPPROVED",ch.getStatus());
    transaction.getTransactionDetails().put("TRNORDERNUMBER",ch.getId());
    transaction.getTransactionDetails().put("MESSAGETEXT",null);
    return transaction;
  }
 catch (  Exception e) {
    throw buildException(e);
  }
}
