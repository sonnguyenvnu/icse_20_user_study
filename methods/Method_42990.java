public DepositAddress requestDepositAddress(Currency currency) throws IOException {
  Long recvWindow=(Long)exchange.getExchangeSpecification().getExchangeSpecificParametersItem("recvWindow");
  return binance.depositAddress(BinanceAdapters.toSymbol(currency),recvWindow,getTimestamp(),apiKey,super.signatureCreator);
}
