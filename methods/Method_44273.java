public GeminiDepositAddressResponse requestDepositAddressRaw(Currency currency) throws IOException {
  try {
    String ccy=convertToGeminiCcyName(currency.getCurrencyCode());
    GeminiDepositAddressRequest exchange=new GeminiDepositAddressRequest(String.valueOf(this.exchange.getNonceFactory().createValue()),ccy,null);
    GeminiDepositAddressResponse requestDepositAddressResponse=gemini.requestNewAddress(apiKey,payloadCreator,signatureCreator,ccy,exchange);
    if (requestDepositAddressResponse != null) {
      return requestDepositAddressResponse;
    }
 else {
      return null;
    }
  }
 catch (  GeminiException e) {
    throw handleException(e);
  }
}
