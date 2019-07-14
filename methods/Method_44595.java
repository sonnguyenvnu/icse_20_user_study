private static List<LimitOrder> convert(Map<BigDecimal,BigDecimal> map,CurrencyPair currencyPair,OrderType type){
  List<LimitOrder> result=new ArrayList<>();
  for (  Entry<BigDecimal,BigDecimal> e : map.entrySet()) {
    result.add(new LimitOrder(type,e.getValue(),currencyPair,null,null,e.getKey()));
  }
  return result;
}
