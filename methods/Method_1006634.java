@Override public Transaction refund(boolean partial,MerchantStore store,Transaction transaction,Order order,BigDecimal amount,IntegrationConfiguration configuration,IntegrationModule module) throws IntegrationException {
  HttpURLConnection conn=null;
  try {
    boolean bSandbox=false;
    if (configuration.getEnvironment().equals("TEST")) {
      bSandbox=true;
    }
    String server="";
    ModuleConfig configs=module.getModuleConfigs().get("PROD");
    if (bSandbox == true) {
      configs=module.getModuleConfigs().get("TEST");
    }
    if (configs == null) {
      throw new IntegrationException("Module not configured for TEST or PROD");
    }
    server=new StringBuffer().append(configs.getScheme()).append("://").append(configs.getHost()).append(":").append(configs.getPort()).append(configs.getUri()).toString();
    String trnID=transaction.getTransactionDetails().get("TRANSACTIONID");
    String amnt=productPriceUtils.getAdminFormatedAmount(store,amount);
    StringBuilder messageString=new StringBuilder();
    messageString.append("requestType=BACKEND&");
    messageString.append("merchant_id=").append(configuration.getIntegrationKeys().get("merchantid")).append("&");
    messageString.append("trnType=").append("R").append("&");
    messageString.append("username=").append(configuration.getIntegrationKeys().get("username")).append("&");
    messageString.append("password=").append(configuration.getIntegrationKeys().get("password")).append("&");
    messageString.append("trnOrderNumber=").append(transaction.getTransactionDetails().get("TRNORDERNUMBER")).append("&");
    messageString.append("trnAmount=").append(amnt).append("&");
    messageString.append("adjId=").append(trnID);
    LOGGER.debug("REQUEST SENT TO BEANSTREAM -> " + messageString.toString());
    URL postURL=new URL(server.toString());
    conn=(HttpURLConnection)postURL.openConnection();
    Transaction response=this.sendTransaction(null,store,messageString.toString(),"R",TransactionType.REFUND,PaymentType.CREDITCARD,amount,configuration,module);
    return response;
  }
 catch (  Exception e) {
    if (e instanceof IntegrationException)     throw (IntegrationException)e;
    throw new IntegrationException("Error while processing BeanStream transaction",e);
  }
 finally {
    if (conn != null) {
      try {
        conn.disconnect();
      }
 catch (      Exception ignore) {
      }
    }
  }
}
