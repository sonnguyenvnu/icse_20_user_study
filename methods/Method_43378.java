public BityResponse<BityOrder> getBityOrders(Long offset,final Integer limit,String orderBy){
  BityExchange bityExchange=(BityExchange)exchange;
  String auth="Bearer " + bityExchange.getToken().getAccessToken();
  return bity.getOrders(offset,limit,orderBy,auth);
}
