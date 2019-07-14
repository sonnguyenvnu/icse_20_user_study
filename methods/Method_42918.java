public BankeraToken createToken() throws BankeraException {
  String clientId=(String)exchange.getExchangeSpecification().getParameter("clientId");
  String clientSecret=(String)exchange.getExchangeSpecification().getParameter("clientSecret");
  return bankera.getToken("client_credentials",clientId,clientSecret);
}
