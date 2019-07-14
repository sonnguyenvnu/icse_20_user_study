private String getControlBeanName(String transactionType,DTXPropagationState lcnTransactionState){
  return String.format(CONTROL_BEAN_NAME_FORMAT,transactionType,lcnTransactionState.getCode());
}
