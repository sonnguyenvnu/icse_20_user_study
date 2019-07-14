public static UserTrade adaptFill(CryptoFacilitiesFill fill){
  return new UserTrade(adaptOrderType(fill.getSide()),fill.getSize(),new CurrencyPair(fill.getSymbol(),fill.getSymbol().substring(6,9)),fill.getPrice(),fill.getFillTime(),fill.getFillId(),fill.getOrderId(),null,(Currency)null);
}
