public CoinbaseProProductBookEntry getBestBid(){
  if (getBids() != null && getBids().length > 0) {
    return getBids()[0];
  }
  return null;
}
