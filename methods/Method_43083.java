List<Balance> getWallets(){
  try {
    BitcointoyouBalance response=bitcointoyouAuthenticated.returnBalances(apiKey,exchange.getNonceFactory(),signatureCreator);
    return BitcointoyouAdapters.adaptBitcointoyouBalances(response);
  }
 catch (  BitcointoyouException e) {
    throw new ExchangeException(e.getError());
  }
}
