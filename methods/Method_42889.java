private static OrderType adaptSide(String side){
switch (side.toUpperCase()) {
case "BUY":
    return OrderType.BID;
case "SELL":
  return OrderType.ASK;
case "BID":
return OrderType.BID;
case "OFFER":
return OrderType.ASK;
case "ASK":
return OrderType.ASK;
default :
throw new IllegalStateException("Don't understand order direction: " + side);
}
}
