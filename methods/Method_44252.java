protected <R extends GateioBaseResponse>R handleResponse(R response){
  if (!response.isResult()) {
    throw new ExchangeException(response.getMessage());
  }
  return response;
}
