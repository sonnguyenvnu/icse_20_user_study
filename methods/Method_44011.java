public CryptoFacilitiesInstruments getCryptoFacilitiesInstruments() throws IOException {
  CryptoFacilitiesInstruments instruments=cryptoFacilities.getInstruments();
  if (instruments.isSuccess()) {
    return instruments;
  }
 else {
    throw new ExchangeException("Error getting CF instruments: " + instruments.getError());
  }
}
