private static OrderType convert(org.knowm.xchange.luno.dto.trade.OrderType type){
switch (type) {
case ASK:
case SELL:
    return OrderType.ASK;
case BID:
case BUY:
  return OrderType.BID;
default :
throw new ExchangeException("Not supported order type: " + type);
}
}
