@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  Bl3pNewDepositAddress newDepositAddress=this.bl3p.createNewDepositAddress(apiKey,signatureCreator,nonceFactory,currency.getCurrencyCode());
  return newDepositAddress.getData().getAddress();
}
