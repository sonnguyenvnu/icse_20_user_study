public String requestItBitDepositAddress(String currency,String... args) throws IOException {
  Map<String,String> metadata=new HashMap<>();
  for (int i=0; i < args.length - 1; i+=2) {
    metadata.put(args[i],args[i + 1]);
  }
  ItBitDepositRequest request=new ItBitDepositRequest(currency,metadata);
  ItBitDepositResponse response=itBitAuthenticated.requestDeposit(signatureCreator,new Date().getTime(),exchange.getNonceFactory(),walletId,request);
  return response.getDepositAddress();
}
