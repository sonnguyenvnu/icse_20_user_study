public ANXBitcoinDepositAddress anxRequestDepositAddress(String currency) throws IOException {
  try {
    ANXBitcoinDepositAddressWrapper anxBitcoinDepositAddressWrapper=anxV2.requestDepositAddress(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),currency);
    return anxBitcoinDepositAddressWrapper.getAnxBitcoinDepositAddress();
  }
 catch (  ANXException e) {
    throw handleError(e);
  }
catch (  HttpStatusIOException e) {
    throw handleHttpError(e);
  }
}
