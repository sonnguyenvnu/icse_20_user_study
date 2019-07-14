@Bean @ConditionalOnMissingBean(TransactionSupportJobWrapper.class) public TransactionSupportJobWrapper transactionSupportJobWrapper(){
  return new SpringTransactionSupportJobWrapper();
}
