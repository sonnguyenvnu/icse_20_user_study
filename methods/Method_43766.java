CoindirectOrder placeExchangeOrder(CoindirectOrderRequest coindirectOrderRequest) throws IOException, CoindirectException {
  return coindirect.placeExchangeOrder(coindirectOrderRequest,signatureCreator);
}
