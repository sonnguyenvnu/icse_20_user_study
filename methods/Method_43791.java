public OrderType getSide(){
  if (isTrade()) {
switch (getAmount().signum()) {
case 0:
default :
      return null;
case 1:
    return OrderType.BID;
case -1:
  return OrderType.ASK;
}
}
 else {
return null;
}
}
