public BitstampOrder placeBitstampOrder(CurrencyPair pair,BitstampAuthenticatedV2.Side side,BigDecimal originalAmount,BigDecimal price) throws IOException {
  try {
    return bitstampAuthenticatedV2.placeOrder(apiKey,signatureCreator,nonceFactory,side,new BitstampV2.Pair(pair),originalAmount,price);
  }
 catch (  BitstampException e) {
    throw handleError(e);
  }
}
