@Override public Transaction refund(boolean partial,MerchantStore store,Transaction transaction,Order order,BigDecimal amount,IntegrationConfiguration configuration,IntegrationModule module) throws IntegrationException {
  String apiKey=configuration.getIntegrationKeys().get("secretKey");
  if (StringUtils.isBlank(apiKey)) {
    IntegrationException te=new IntegrationException("Can't process Stripe, missing payment.metaData");
    te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
    te.setMessageCode("message.payment.error");
    te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
    throw te;
  }
  try {
    String trnID=transaction.getTransactionDetails().get("TRNORDERNUMBER");
    String amnt=productPriceUtils.getAdminFormatedAmount(store,amount);
    Stripe.apiKey=apiKey;
    String strAmount=String.valueOf(amnt);
    strAmount=strAmount.replace(".","");
    Map params=new HashMap();
    params.put("amount",strAmount);
    Charge ch=Charge.retrieve(trnID);
    Refund re=ch.getRefunds().create(params);
    transaction=new Transaction();
    transaction.setAmount(order.getTotal());
    transaction.setOrder(order);
    transaction.setTransactionDate(new Date());
    transaction.setTransactionType(TransactionType.CAPTURE);
    transaction.setPaymentType(PaymentType.CREDITCARD);
    transaction.getTransactionDetails().put("TRANSACTIONID",transaction.getTransactionDetails().get("TRANSACTIONID"));
    transaction.getTransactionDetails().put("TRNAPPROVED",re.getReason());
    transaction.getTransactionDetails().put("TRNORDERNUMBER",re.getId());
    transaction.getTransactionDetails().put("MESSAGETEXT",null);
    return transaction;
  }
 catch (  Exception e) {
    throw buildException(e);
  }
}
