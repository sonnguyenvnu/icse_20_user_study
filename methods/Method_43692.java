public static OrderStatus[] adaptOrderStatuses(CoinbaseProOrder[] orders){
  OrderStatus[] orderStatuses=new OrderStatus[orders.length];
  Integer i=0;
  for (  CoinbaseProOrder coinbaseProOrder : orders) {
    orderStatuses[i++]=adaptOrderStatus(coinbaseProOrder);
  }
  return orderStatuses;
}
