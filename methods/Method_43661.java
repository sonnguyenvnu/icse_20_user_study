@Override public String requestDepositAddress(Currency currency,String... arguments) throws IOException {
  final CoinbaseAddress receiveAddress=super.getCoinbaseReceiveAddress();
  return receiveAddress.getAddress();
}
