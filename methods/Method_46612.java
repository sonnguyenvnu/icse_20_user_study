public TransactionCleanService loadTransactionCleanService(String transactionType){
  return spring.getBean(String.format(TRANSACTION_CLEAN_SERVICE_NAME_FORMAT,transactionType),TransactionCleanService.class);
}
