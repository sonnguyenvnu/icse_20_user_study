public List<BleutradeTicker> getBleutradeTickers() throws IOException {
  BleutradeTickerReturn response=bleutrade.getBleutradeTickers();
  if (!response.getSuccess()) {
    throw new ExchangeException(response.getMessage());
  }
  return response.getResult();
}
