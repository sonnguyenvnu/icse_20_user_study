public CexIOBalanceInfo getCexIOAccountInfo() throws IOException {
  CexIOBalanceInfo info=cexIOAuthenticated.getBalance(signatureCreator,new CexIORequest());
  if (info.getError() != null) {
    throw new ExchangeException("Error getting balance. " + info.getError());
  }
  return info;
}
