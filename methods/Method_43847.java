public CoinmateDepositAddresses coinmateBitcoinDepositAddresses() throws IOException {
  CoinmateDepositAddresses addresses=coinmateAuthenticated.bitcoinDepositAddresses(exchange.getExchangeSpecification().getApiKey(),exchange.getExchangeSpecification().getUserName(),signatureCreator,exchange.getNonceFactory());
  throwExceptionIfError(addresses);
  return addresses;
}
