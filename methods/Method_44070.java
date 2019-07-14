public long symbolId(CurrencyPair pair){
  Long symbolId=pairs.get(pair);
  if (symbolId == null) {
    throw new ExchangeException("Not supported pair " + pair + " by Dragonex.");
  }
  return symbolId;
}
