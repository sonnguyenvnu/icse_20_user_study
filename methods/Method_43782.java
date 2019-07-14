public CoinEggTradeView getCoinEggTradeView(String tradeID,String coin) throws IOException {
  return coinEggAuthenticated.getTradeView(apiKey,signer,nonceFactory.createValue(),tradeID,coin);
}
