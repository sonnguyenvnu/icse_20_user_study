@Override public RippleTradeHistoryParams createTradeHistoryParams(){
  final RippleTradeHistoryParams params=new RippleTradeHistoryParams();
  params.setAccount(exchange.getExchangeSpecification().getApiKey());
  return params;
}
