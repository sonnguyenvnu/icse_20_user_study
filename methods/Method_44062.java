private String extractContractName(DeribitInstrument instrument){
  String[] temp=instrument.getInstrumentName().split("-",2);
  if (temp.length > 0) {
    return temp[1];
  }
  throw new ExchangeException("Extracting contract name failed");
}
