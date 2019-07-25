@Override public Transaction authorize(MerchantStore store,Customer customer,List<ShoppingCartItem> items,BigDecimal amount,Payment payment,IntegrationConfiguration configuration,IntegrationModule module) throws IntegrationException {
  Transaction transaction=new Transaction();
  try {
    String apiKey=configuration.getIntegrationKeys().get("secretKey");
    if (payment.getPaymentMetaData() == null || StringUtils.isBlank(apiKey)) {
      IntegrationException te=new IntegrationException("Can't process Stripe, missing payment.metaData");
      te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
      te.setMessageCode("message.payment.error");
      te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
      throw te;
    }
    String token=payment.getPaymentMetaData().get("stripe_token");
    if (StringUtils.isBlank(token)) {
      IntegrationException te=new IntegrationException("Can't process Stripe, missing stripe token");
      te.setExceptionType(IntegrationException.TRANSACTION_EXCEPTION);
      te.setMessageCode("message.payment.error");
      te.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
      throw te;
    }
    String amnt=productPriceUtils.getAdminFormatedAmount(store,amount);
    String strAmount=String.valueOf(amnt);
    strAmount=strAmount.replace(".","");
    Map<String,Object> chargeParams=new HashMap<String,Object>();
    chargeParams.put("amount",strAmount);
    chargeParams.put("capture",false);
    chargeParams.put("currency",store.getCurrency().getCode());
    chargeParams.put("source",token);
    chargeParams.put("description",new StringBuilder().append(TRANSACTION).append(" - ").append(store.getStorename()).toString());
    Stripe.apiKey=apiKey;
    Charge ch=Charge.create(chargeParams);
    transaction.setAmount(amount);
    transaction.setTransactionDate(new Date());
    transaction.setTransactionType(TransactionType.AUTHORIZE);
    transaction.setPaymentType(PaymentType.CREDITCARD);
    transaction.getTransactionDetails().put("TRANSACTIONID",token);
    transaction.getTransactionDetails().put("TRNAPPROVED",ch.getStatus());
    transaction.getTransactionDetails().put("TRNORDERNUMBER",ch.getId());
    transaction.getTransactionDetails().put("MESSAGETEXT",null);
  }
 catch (  Exception e) {
    throw buildException(e);
  }
  return transaction;
}
