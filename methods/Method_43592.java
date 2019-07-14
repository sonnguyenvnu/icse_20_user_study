public Map<CurrencyPair,FeeDetails> getMyFee() throws IOException {
  return cexIOAuthenticated.getMyFee(signatureCreator,new CexIORequest()).getData();
}
