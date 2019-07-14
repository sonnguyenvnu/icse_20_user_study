protected Trades mapKunaTrades2Trades(KunaTrade[] kunaTrades,CurrencyPair currencyPair){
  List<Trade> trades=Arrays.stream(kunaTrades).map(kunaTrade -> mapKunaTrade2Trade(kunaTrade,currencyPair)).collect(Collectors.toList());
  return new Trades(trades);
}
