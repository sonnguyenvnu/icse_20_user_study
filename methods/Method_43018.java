public static List<UserTrade> adaptTransactions(List<Map> response){
  List<UserTrade> trades=new ArrayList<>();
  for (  Map map : response) {
    try {
      OrderType orderType;
      String type=map.get("type").toString();
      if (type.equals("BID"))       orderType=OrderType.BID;
 else       if (type.equals("ASK"))       orderType=OrderType.ASK;
 else       continue;
      String market=map.get("market").toString();
      String[] parts=market.split("-");
      CurrencyPair pair=new CurrencyPair(Currency.getInstance(parts[0]),Currency.getInstance(parts[1]));
      DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String date=map.get("date").toString();
      Date timestamp=formatter.parse(date);
      BigDecimal amount=new BigDecimal(map.get("amount").toString());
      BigDecimal price=new BigDecimal(map.get("rate").toString());
      String id=(type + "_" + date + "_" + market).replaceAll("\\s+","");
      trades.add(new UserTrade(orderType,amount,pair,price,timestamp,id,null,null,null));
    }
 catch (    ParseException e) {
      throw new IllegalStateException("Cannot parse " + map);
    }
  }
  return trades;
}
