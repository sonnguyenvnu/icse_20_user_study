private static CoinbaseProProductBookEntry convertToBookEntry(Object[] dataObject){
  if (dataObject != null && dataObject.length == 3) {
    BigDecimal price=new BigDecimal((String)dataObject[0]);
    BigDecimal volume=new BigDecimal((String)dataObject[1]);
    if (dataObject[2] instanceof String) {
      return new CoinbaseProProductBookEntryLevel3(price,volume,(String)dataObject[2]);
    }
 else {
      int numberOfOrders=(Integer)dataObject[2];
      return new CoinbaseProProductBookEntryLevel1or2(price,volume,numberOfOrders);
    }
  }
  return null;
}
