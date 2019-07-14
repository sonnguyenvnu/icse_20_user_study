public List<BiboxOrderBook> getBiboxOrderBooks(Integer depth,Collection<CurrencyPair> currencyPairs){
  try {
    List<BiboxCommand<?>> allCommands=currencyPairs.stream().distinct().filter(Objects::nonNull).map(BiboxAdapters::toBiboxPair).map(pair -> new BiboxOrderBookCommand(pair,depth)).collect(Collectors.toList());
    BiboxMultipleResponses<BiboxOrderBook> response=bibox.orderBooks(BiboxCommands.of(allCommands).json());
    throwErrors(response);
    return response.getResult().stream().map(BiboxResponse::getResult).collect(Collectors.toList());
  }
 catch (  BiboxException e) {
    throw new ExchangeException(e.getMessage());
  }
}
