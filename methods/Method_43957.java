static OrderQueryParams[] toOrderQueryParams(String... orderIds){
  OrderQueryParams[] res=new OrderQueryParams[orderIds.length];
  for (int i=0; i < orderIds.length; i++) {
    String orderId=orderIds[i];
    res[i]=new DefaultQueryOrderParam(orderId);
  }
  return res;
}
