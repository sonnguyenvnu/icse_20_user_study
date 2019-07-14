public GateioFunds getGateioAccountInfo() throws IOException {
  GateioFunds gateioFunds=bter.getFunds(exchange.getExchangeSpecification().getApiKey(),signatureCreator);
  return handleResponse(gateioFunds);
}
