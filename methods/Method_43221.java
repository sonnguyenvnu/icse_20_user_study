public static BitmexDepth adaptDepth(BitmexPublicOrderList orders){
  BitmexDepth bitmexDepth=new BitmexDepth(new ArrayList<>(),new ArrayList<>());
  for (  BitmexPublicOrder bitmexOrder : orders) {
    if (bitmexOrder.getSide().equals(BitmexSide.BUY)) {
      bitmexDepth.getBids().add(bitmexOrder);
    }
 else     if (bitmexOrder.getSide().equals(BitmexSide.SELL)) {
      bitmexDepth.getAsks().add(bitmexOrder);
    }
  }
  return bitmexDepth;
}
