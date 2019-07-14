public String getBittrexDepositAddress(String currency) throws IOException {
  return bittrexAuthenticated.getdepositaddress(apiKey,signatureCreator,exchange.getNonceFactory(),currency).getResult().getAddress();
}
