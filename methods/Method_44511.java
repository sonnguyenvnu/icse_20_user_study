protected Trade mapKunaTrade2Trade(KunaTrade kunaTrade,CurrencyPair currencyPair){
  Trade.Builder builder=new Trade.Builder().currencyPair(currencyPair).id(String.valueOf(kunaTrade.getId())).price(kunaTrade.getPrice()).timestamp(kunaTrade.getCreatedAt()).originalAmount(kunaTrade.getVolume());
  return builder.build();
}
