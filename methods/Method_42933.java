/** 
 * transaction side?1-bid?2-ask
 * @param side
 * @return
 */
private static OrderType convertSide(int side){
switch (side) {
case 1:
    return OrderType.BID;
case 2:
  return OrderType.ASK;
default :
throw new RuntimeException("Unknown order type (side) of bibox deal: " + side);
}
}
