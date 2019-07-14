public DVChainMarketResponse getMarketData() throws IOException {
  try {
    return dvChain.getPrices(authToken);
  }
 catch (  DVChainException e) {
    throw handleException(e);
  }
}
