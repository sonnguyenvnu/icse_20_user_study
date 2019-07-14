public String getDepositAddress(String currency) throws IOException {
  HashMap<String,String> response=poloniexAuthenticated.returnDepositAddresses(apiKey,signatureCreator,exchange.getNonceFactory());
  if (response.containsKey("error")) {
    throw new PoloniexException(response.get("error"));
  }
  if (response.containsKey(currency)) {
    return response.get(currency);
  }
 else {
    PoloniexGenerateNewAddressResponse newAddressResponse=poloniexAuthenticated.generateNewAddress(apiKey,signatureCreator,exchange.getNonceFactory(),currency);
    if (newAddressResponse.success()) {
      return newAddressResponse.getAddress();
    }
 else {
      throw new PoloniexException("Failed to get Poloniex deposit address for " + currency);
    }
  }
}
