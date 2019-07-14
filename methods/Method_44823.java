public boolean isStoreTradeTransactionDetails(){
  return (Boolean)getExchangeSpecification().getExchangeSpecificParametersItem(PARAMETER_STORE_TRADE_TRANSACTION_DETAILS);
}
