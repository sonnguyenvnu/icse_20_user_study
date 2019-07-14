public Map<String,LiquiTicker> getAllTickers(){
  return getTicker(new ArrayList<>(exchange.getExchangeMetaData().getCurrencyPairs().keySet()));
}
