private Balance mapBalance(AcxAccount acc){
  return new Balance(Currency.getInstance(acc.currency),acc.balance.add(acc.locked),acc.balance,acc.locked);
}
