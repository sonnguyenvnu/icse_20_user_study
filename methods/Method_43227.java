private static OrderType convertType(String side){
switch (side) {
case "Buy":
    return OrderType.BID;
case "Sell":
  return OrderType.ASK;
default :
return null;
}
}
