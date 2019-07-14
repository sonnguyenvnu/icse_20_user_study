public BitstampUserTransaction[] getBitstampUserTransactions(Long numberOfTransactions,Long offset,String sort,Long sinceTimestamp) throws IOException {
  try {
    return bitstampAuthenticatedV2.getUserTransactions(apiKey,signatureCreator,nonceFactory,numberOfTransactions,offset,sort,sinceTimestamp);
  }
 catch (  BitstampException e) {
    throw handleError(e);
  }
}
