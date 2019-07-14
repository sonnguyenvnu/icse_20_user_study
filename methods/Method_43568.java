public String getCCEXDepositAddress(String currency) throws IOException {
  CCEXBalanceResponse response=cCEXAuthenticated.getdepositaddress(apiKey,signatureCreator,exchange.getNonceFactory(),currency);
  if (response.isSuccess()) {
    return response.getResult().getCryptoAddress();
  }
 else {
    throw new ExchangeException(response.getMessage());
  }
}
