public Map<String,String> depositAddresses(){
  return exmo.depositAddress(signatureCreator,apiKey,exchange.getNonceFactory());
}
