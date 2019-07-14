public CoinbaseProProductBookEntry getBestAsk(){
  if (getAsks() != null && getAsks().length > 0) {
    return getAsks()[0];
  }
  return null;
}
