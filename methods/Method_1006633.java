@Override public Transaction capture(MerchantStore store,Customer customer,Order order,Transaction capturableTransaction,IntegrationConfiguration configuration,IntegrationModule module) throws IntegrationException {
  try {
    String trnID=capturableTransaction.getTransactionDetails().get("TRANSACTIONID");
    String amnt=productPriceUtils.getAdminFormatedAmount(store,order.getTotal());
    StringBuilder messageString=new StringBuilder();
    messageString.append("requestType=BACKEND&");
    messageString.append("merchant_id=").append(configuration.getIntegrationKeys().get("merchantid")).append("&");
    messageString.append("trnType=").append("PAC").append("&");
    messageString.append("username=").append(configuration.getIntegrationKeys().get("username")).append("&");
    messageString.append("password=").append(configuration.getIntegrationKeys().get("password")).append("&");
    messageString.append("trnAmount=").append(amnt).append("&");
    messageString.append("adjId=").append(trnID).append("&");
    messageString.append("trnID=").append(trnID);
    LOGGER.debug("REQUEST SENT TO BEANSTREAM -> " + messageString.toString());
    Transaction response=this.sendTransaction(null,store,messageString.toString(),"PAC",TransactionType.CAPTURE,PaymentType.CREDITCARD,order.getTotal(),configuration,module);
    return response;
  }
 catch (  Exception e) {
    if (e instanceof IntegrationException)     throw (IntegrationException)e;
    throw new IntegrationException("Error while processing BeanStream transaction",e);
  }
}
