public List<ZaifMarket> getAllMarkets(){
  try {
    return this.zaif.getCurrencyPairs();
  }
 catch (  ZaifException e) {
    throw new ExchangeException(e.getMessage());
  }
}
