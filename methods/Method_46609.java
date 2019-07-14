public DTXLocalControl loadDTXLocalControl(String transactionType,DTXPropagationState lcnTransactionState){
  return loadDTXLocalControl(getControlBeanName(transactionType,lcnTransactionState));
}
