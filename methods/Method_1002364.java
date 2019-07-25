public void apply(feign.RequestTemplate template){
  final SpringCloudBeanRegistry beanRegistry=SpringCloudBeanRegistry.getInstance();
  CompensableBeanFactory beanFactory=beanRegistry.getBeanFactory();
  CompensableManager compensableManager=beanFactory.getCompensableManager();
  CompensableTransaction compensable=compensableManager.getCompensableTransactionQuietly();
  if (compensable == null) {
    return;
  }
  try {
    TransactionContext transactionContext=compensable.getTransactionContext();
    byte[] byteArray=SerializeUtils.serializeObject(transactionContext);
    String transactionText=Base64.getEncoder().encodeToString(byteArray);
    Map<String,Collection<String>> headers=template.headers();
    if (headers.containsKey(HEADER_TRANCACTION_KEY) == false) {
      template.header(HEADER_TRANCACTION_KEY,transactionText);
    }
    if (headers.containsKey(HEADER_PROPAGATION_KEY) == false) {
      template.header(HEADER_PROPAGATION_KEY,identifier);
    }
  }
 catch (  IOException ex) {
    throw new RuntimeException("Error occurred while preparing the transaction context!",ex);
  }
}
