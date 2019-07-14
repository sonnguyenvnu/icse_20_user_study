public String getCurrency(long coinId){
  String currency=coins.get(coinId);
  if (currency == null) {
    throw new RuntimeException("Could not find the currency for coin id: " + coinId);
  }
  return currency;
}
