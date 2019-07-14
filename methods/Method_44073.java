public long getCoinId(String currency){
  Long coinId=coinsReverse.get(currency);
  if (coinId == null) {
    throw new RuntimeException("Could not find the coin id for " + currency);
  }
  return coinId;
}
