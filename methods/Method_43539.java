public CampBXResponse requestCampBXBitcoinDepositAddress() throws IOException {
  CampBXResponse campBXResponse=campBX.getDepositAddress(exchange.getExchangeSpecification().getUserName(),exchange.getExchangeSpecification().getPassword());
  return campBXResponse;
}
