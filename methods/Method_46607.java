public TransactionResourceProxy loadTransactionResourceProxy(String beanName){
  String name=String.format(TRANSACTION_BEAN_NAME_FORMAT,beanName);
  return spring.getBean(name,TransactionResourceProxy.class);
}
