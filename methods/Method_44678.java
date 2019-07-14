@Override public Collection<Order> getOrder(OrderQueryParams... orderQueryParams) throws IOException {
  Map<CurrencyPair,Map<FuturesContract,Set<String>>> ordersToQuery=new HashMap<CurrencyPair,Map<FuturesContract,Set<String>>>();
  List<String> orderIdsRequest=new ArrayList<>();
  List<OkCoinFuturesOrder> orderResults=new ArrayList<>();
  List<Order> openOrders=new ArrayList<>();
  for (  OrderQueryParams orderQueryParam : orderQueryParams) {
    OkCoinFuturesOrderQueryParams myParams=(OkCoinFuturesOrderQueryParams)orderQueryParam;
    CurrencyPair currencyPair=myParams.getCurrencyPair();
    FuturesContract reqFuturesContract=myParams.futuresContract;
    long orderId=myParams.getOrderId() != null ? Long.valueOf(myParams.getOrderId()) : -1;
    if (ordersToQuery.get(currencyPair) == null) {
      Set<String> orderSet=new HashSet<>();
      orderSet.add(String.valueOf(orderId));
      HashMap<FuturesContract,Set<String>> futuresContractMap=new HashMap<FuturesContract,Set<String>>();
      futuresContractMap.put(reqFuturesContract,orderSet);
      ordersToQuery.put(currencyPair,futuresContractMap);
    }
 else     if (ordersToQuery.get(currencyPair).get(reqFuturesContract) == null) {
      Set<String> orderSet=new HashSet<>();
      orderSet.add(String.valueOf(orderId));
      ordersToQuery.get(currencyPair).put(reqFuturesContract,orderSet);
    }
 else {
      ordersToQuery.get(currencyPair).get(reqFuturesContract).add(String.valueOf(orderId));
    }
  }
  for (  CurrencyPair pair : ordersToQuery.keySet()) {
    for (    FuturesContract contract : ordersToQuery.get(pair).keySet()) {
      int count=0;
      orderIdsRequest.clear();
      for (      String order : ordersToQuery.get(pair).get(contract)) {
        orderIdsRequest.add(order);
        count++;
        if (count % batchSize == 0) {
          OkCoinFuturesOrderResult orderResult=getFuturesOrders(createDelimitedString(orderIdsRequest.toArray(new String[orderIdsRequest.size()])),OkCoinAdapters.adaptSymbol(pair),contract);
          orderIdsRequest.clear();
          if (orderResult.getOrders().length > 0) {
            orderResults.addAll(new ArrayList<>(Arrays.asList(orderResult.getOrders())));
          }
        }
      }
      OkCoinFuturesOrderResult orderResult;
      if (!orderIdsRequest.isEmpty()) {
        orderResult=getFuturesOrders(createDelimitedString(orderIdsRequest.toArray(new String[orderIdsRequest.size()])),OkCoinAdapters.adaptSymbol(pair),contract);
      }
 else {
        orderResult=getFuturesFilledOrder(-1,OkCoinAdapters.adaptSymbol(pair),"0","50",contract);
      }
      if (orderResult.getOrders().length > 0) {
        for (int o=0; o < orderResult.getOrders().length; o++) {
          OkCoinFuturesOrder singleOrder=orderResult.getOrders()[o];
          openOrders.add(OkCoinAdapters.adaptOpenOrderFutures(singleOrder));
        }
      }
    }
  }
  return openOrders;
}
