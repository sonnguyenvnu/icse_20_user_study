public OrderType getSide(){
switch (type) {
case 0:
    return OrderType.BID;
case 1:
  return OrderType.ASK;
default :
return null;
}
}
