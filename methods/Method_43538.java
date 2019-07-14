public MyFunds getCampBXAccountInfo() throws IOException {
  MyFunds myFunds=campBX.getMyFunds(exchange.getExchangeSpecification().getUserName(),exchange.getExchangeSpecification().getPassword());
  return myFunds;
}
