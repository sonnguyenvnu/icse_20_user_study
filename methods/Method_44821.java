@Override public ExchangeSpecification getDefaultExchangeSpecification(){
  final ExchangeSpecification specification=new ExchangeSpecification(this.getClass().getCanonicalName());
  specification.setSslUri(REST_API_RIPPLE_LABS);
  specification.setExchangeName("Ripple");
  specification.setExchangeDescription("Ripple is a payment system, currency exchange and remittance network");
  specification.setExchangeSpecificParametersItem(PARAMETER_TRUST_API_RIPPLE_COM,false);
  specification.setExchangeSpecificParametersItem(PARAMETER_STORE_TRADE_TRANSACTION_DETAILS,false);
  specification.setExchangeSpecificParametersItem(PARAMETER_VALIDATE_ORDER_REQUESTS,true);
  specification.setExchangeSpecificParametersItem(PARAMETER_ROUNDING_SCALE,DEFAULT_ROUNDING_SCALE);
  return specification;
}
