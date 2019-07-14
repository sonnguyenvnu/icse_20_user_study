public DVChainTrade newDVChainMarketOrder(DVChainNewMarketOrder order) throws IOException {
  try {
    return dvChain.placeMarketOrder(order,authToken);
  }
 catch (  DVChainException e) {
    throw handleException(e);
  }
}
