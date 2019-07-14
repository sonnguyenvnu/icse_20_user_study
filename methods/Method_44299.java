private static UserTrade adaptToUserTrade(GlobitexUserTrade globitexUserTrade){
  return new UserTrade((globitexUserTrade.getSide().equals("sell") ? Order.OrderType.ASK : Order.OrderType.BID),globitexUserTrade.getQuantity(),CurrencyPairDeserializer.getCurrencyPairFromString(convertXBTtoBTC(globitexUserTrade.getSymbol())),globitexUserTrade.getPrice(),new Date(globitexUserTrade.getTimestamp()),String.valueOf(globitexUserTrade.getOriginalOrderId()),globitexUserTrade.getClientOrderId(),globitexUserTrade.getFee(),new Currency(convertXBTtoBTC(globitexUserTrade.getFeeCurrency())));
}
