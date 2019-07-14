public org.knowm.xchange.coinbasepro.dto.account.CoinbaseProAccount[] getCoinbaseProAccountInfo() throws CoinbaseProException, IOException {
  return coinbasePro.getAccounts(apiKey,digest,nonceFactory,passphrase);
}
