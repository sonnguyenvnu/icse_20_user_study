@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  CampBXResponse campBXResponse=requestCampBXBitcoinDepositAddress();
  logger.debug("campBXResponse = {}",campBXResponse);
  if (!campBXResponse.isError()) {
    return campBXResponse.getSuccess();
  }
 else {
    throw new ExchangeException("Error calling requestBitcoinDepositAddress(): " + campBXResponse.getError());
  }
}
