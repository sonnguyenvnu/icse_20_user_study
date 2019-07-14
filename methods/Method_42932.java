public static Trades adaptDeals(List<BiboxDeals> biboxDeals,CurrencyPair currencyPair){
  List<Trade> trades=biboxDeals.stream().map(d -> new Trade(convertSide(d.getSide()),d.getAmount(),currencyPair,d.getPrice(),new Date(d.getTime()),d.getId())).collect(Collectors.toList());
  return new Trades(trades,TradeSortType.SortByTimestamp);
}
