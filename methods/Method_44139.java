public DVChainTrade newDVChainLimitOrder(DVChainNewLimitOrder order) throws IOException {
  try {
    return dvChain.placeLimitOrder(order,authToken);
  }
 catch (  DVChainException e) {
    throw handleException(e);
  }
}
