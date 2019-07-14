public GeminiBalancesResponse[] getGeminiAccountInfo() throws IOException {
  try {
    GeminiBalancesRequest request=new GeminiBalancesRequest(String.valueOf(exchange.getNonceFactory().createValue()));
    GeminiBalancesResponse[] balances=gemini.balances(apiKey,payloadCreator,signatureCreator,request);
    return balances;
  }
 catch (  GeminiException e) {
    throw handleException(e);
  }
}
